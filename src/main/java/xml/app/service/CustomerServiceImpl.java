package xml.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xml.app.domain.entity.Customer;
import xml.app.domain.entity.Part;
import xml.app.domain.entity.Sale;
import xml.app.domain.model.binding.CustomerSeedDto;
import xml.app.domain.model.binding.rootDto.CustomerSeedRootDto;
import xml.app.domain.model.view.query1.OrderedCustomerDto;
import xml.app.domain.model.view.query1.OrderedCustomerRootDto;
import xml.app.domain.model.view.query5.CustomerTotalSalesDto;
import xml.app.domain.model.view.query5.CustomerTotalSalesRootDto;
import xml.app.repository.CustomerRepository;
import xml.app.service.contract.CustomerService;
import xml.app.util.contract.FileIO;
import xml.app.util.contract.ValidatorUtil;
import xml.app.util.contract.XmlParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static xml.app.constant.FilePath.CUSTOMERS_TOTAL_SALES_VIEW;
import static xml.app.constant.FilePath.ORDERED_CUSTOMERS_VIEW;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final FileIO fileIO;

    public CustomerServiceImpl(CustomerRepository customerRepository, ValidatorUtil validatorUtil,
                               ModelMapper modelMapper, XmlParser xmlParser, FileIO fileIO) {
        this.customerRepository = customerRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.fileIO = fileIO;
    }

    @Override
    public void seedCustomers(String xmlCustomers) {
        if (this.customerRepository.count() != 0) {
            return;
        }
        CustomerSeedRootDto customerSeedDtos = this.xmlParser.parse(CustomerSeedRootDto.class, xmlCustomers);

        for (CustomerSeedDto customerSeedDto : customerSeedDtos.getCustomerSeedDtos()) {
            if (this.validatorUtil.ifNotValidPrintViolations(customerSeedDto)) {
                return;
            }
            Customer customer = this.modelMapper.map(customerSeedDto, Customer.class);
            customer.setBirthDate(LocalDate.parse(customerSeedDto.getBirthDate(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
            this.customerRepository.saveAndFlush(customer);
        }
    }

    @Override
    public int getCount() {
        return Math.toIntExact(this.customerRepository.count());
    }

    @Override
    public Customer findCustomerById(Integer id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer getRandomCustomer() {
        Random random = new Random();
        return this.findCustomerById(random.nextInt(this.getCount()) + 1);
    }

    @Override
    public String getOrderedCustomers() {
        List<Customer> customers = this.customerRepository.orderedCustomers();

        OrderedCustomerRootDto orderedCustomerRootDto = new OrderedCustomerRootDto();

        for (Customer customer : customers) {
            orderedCustomerRootDto.getOrderedCustomerDtos()
                    .add(this.modelMapper.map(customer, OrderedCustomerDto.class));
        }

        this.xmlParser.export(orderedCustomerRootDto,OrderedCustomerRootDto.class, ORDERED_CUSTOMERS_VIEW);
        return this.fileIO.readFile(ORDERED_CUSTOMERS_VIEW);
    }

    @Override
    @Transactional
    public String getCustomersTotalSales() {
        List<Customer> customers = this.customerRepository.customerTotalSales();
        CustomerTotalSalesRootDto customerTotalSalesRootDto = new CustomerTotalSalesRootDto();

        for (Customer customer : customers) {
            customerTotalSalesRootDto.getCustomerTotalSalesDtos()
                    .add(this.modelMapper.map(customer, CustomerTotalSalesDto.class));
        }
        for (int i = 0; i < customers.size(); i++) {
            customerTotalSalesRootDto.getCustomerTotalSalesDtos()
                    .get(i).setBoughtCars(customers.get(i).getSales().size());
            double spentMoney = 0;
            for (Sale sale : customers.get(i).getSales()) {
                double totalPriceSale = 0;
                for (Part part : sale.getCar().getParts()) {
                    totalPriceSale += part.getPrice().doubleValue();
                }
                spentMoney += totalPriceSale - totalPriceSale * sale.getDiscount();
            }
            customerTotalSalesRootDto.getCustomerTotalSalesDtos()
                    .get(i).setSpentMoney(this.round(spentMoney));
        }
        customerTotalSalesRootDto.getCustomerTotalSalesDtos()
                .sort(Comparator.comparing(CustomerTotalSalesDto::getBoughtCars).reversed());

        this.xmlParser.export(customerTotalSalesRootDto, CustomerTotalSalesRootDto.class, CUSTOMERS_TOTAL_SALES_VIEW);
        return this.fileIO.readFile(CUSTOMERS_TOTAL_SALES_VIEW);
    }

    @Override
    public double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
