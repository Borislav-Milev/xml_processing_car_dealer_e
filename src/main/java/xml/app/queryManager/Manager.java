package xml.app.queryManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xml.app.service.contract.CarService;
import xml.app.service.contract.CustomerService;
import xml.app.service.contract.SaleService;
import xml.app.service.contract.SupplierService;
import xml.app.util.contract.Reader;

@Component
public class Manager implements Runnable{

    private final CustomerService customerService;
    private final SupplierService supplierService;
    private final SaleService saleService;
    private final CarService carService;
    private final Reader reader;

    @Autowired
    public Manager(CustomerService customerService, SupplierService supplierService,
                   SaleService saleService, CarService carService, Reader reader) {
        this.customerService = customerService;
        this.supplierService = supplierService;
        this.saleService = saleService;
        this.carService = carService;
        this.reader = reader;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Please enter query from 1 to 6 you wish to test, or 0 to exit.");
            try {
                int input = Integer.parseInt(this.reader.getReader());
                if(input == 0) break;
                switch (input) {
                    case 1 -> System.out.println(this.customerService.getOrderedCustomers());
                    case 2 -> System.out.println(this.carService.getToyotaCars());
                    case 3 -> System.out.println(this.supplierService.getLocalSuppliers());
                    case 4 -> System.out.println(this.carService.getCarsAndParts());
                    case 5 -> System.out.println(this.customerService.getCustomersTotalSales());
                    case 6 -> System.out.println(this.saleService.getSalesWithDiscount());
                    default -> System.out.println("No such exercise.");
                }
            }catch (NumberFormatException e){
                System.out.println("Number required.");
            }
        }
    }

}
