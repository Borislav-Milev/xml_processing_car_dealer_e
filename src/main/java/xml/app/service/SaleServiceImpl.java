package xml.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xml.app.domain.entity.Car;
import xml.app.domain.entity.Customer;
import xml.app.domain.entity.Part;
import xml.app.domain.entity.Sale;
import xml.app.domain.model.view.query6.SaleWithDiscountDto;
import xml.app.domain.model.view.query6.SalesWithDiscountRootDto;
import xml.app.repository.SaleRepository;
import xml.app.service.contract.CarService;
import xml.app.service.contract.CustomerService;
import xml.app.service.contract.SaleService;
import xml.app.util.contract.FileIO;
import xml.app.util.contract.XmlParser;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static xml.app.constant.FilePath.SALES_DISCOUNTS_VIEW;


@Service
public class SaleServiceImpl implements SaleService {

    private final CustomerService customerService;
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;
    private final XmlParser xmlParser;
    private final FileIO fileIO;

    public SaleServiceImpl(SaleRepository saleRepository, CarService carService, CustomerService customerService,
                           ModelMapper modelMapper, XmlParser xmlParser, FileIO fileIO) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.fileIO = fileIO;
    }

    @Override
    @Transactional
    public void seedSales() {
        if (this.saleRepository.count() != 0) {
            return;
        }
        Double[] discounts = new Double[]{1d, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5};

        for (Car car : this.carService.getAllCars()) {
            Random random = new Random();
            Sale sale = new Sale();
            sale.setCar(car);
            Customer customer = this.customerService.getRandomCustomer();
            sale.setCustomer(customer);

            if (customer.getIsYoungDriver()) {
                sale.setDiscount(Arrays.copyOfRange(discounts, 1, discounts.length)
                        [random.nextInt(discounts.length - 1)]);
            } else sale.setDiscount(discounts[random.nextInt(discounts.length)]);

            this.saleRepository.saveAndFlush(sale);
        }
    }

    @Override
    @Transactional
    public String getSalesWithDiscount() {
        List<Sale> sales = this.saleRepository.salesDiscounts();
        SalesWithDiscountRootDto salesWithDiscountRootDto = new SalesWithDiscountRootDto();

        for (Sale sale : sales) {
            salesWithDiscountRootDto.getSaleWithDiscountDtos()
                    .add(this.modelMapper.map(sale, SaleWithDiscountDto.class));
        }
        for (int i = 0; i < sales.size(); i++) {

            double totalPriceSale = 0;
            for (Part part : sales.get(i).getCar().getParts()) {
                totalPriceSale += part.getPrice().doubleValue();
            }

            salesWithDiscountRootDto.getSaleWithDiscountDtos()
                    .get(i).setPriceWithoutDiscount(this.customerService.round(totalPriceSale));
            totalPriceSale -= totalPriceSale * sales.get(i).getDiscount();
            salesWithDiscountRootDto.getSaleWithDiscountDtos()
                    .get(i).setPrice(this.customerService.round(totalPriceSale));
        }

        this.xmlParser.export(salesWithDiscountRootDto, SalesWithDiscountRootDto.class, SALES_DISCOUNTS_VIEW);
        return this.fileIO.readFile(SALES_DISCOUNTS_VIEW);
    }


}
