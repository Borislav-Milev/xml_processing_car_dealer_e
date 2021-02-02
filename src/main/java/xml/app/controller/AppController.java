package xml.app.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xml.app.queryManager.Manager;
import xml.app.service.contract.*;
import xml.app.util.contract.FileIO;

import static xml.app.constant.FilePath.*;


@Component
public class AppController implements CommandLineRunner {

    private final Manager manager;
    private final CustomerService customerService;
    private final SupplierService supplierService;
    private final PartService partService;
    private final SaleService saleService;
    private final CarService carService;
    private final FileIO fileIO;

    public AppController(Manager manager, CustomerService customerService, SupplierService supplierService,
                         CarService carService, PartService partService, SaleService saleService, FileIO fileIO) {
        this.manager = manager;
        this.customerService = customerService;
        this.supplierService = supplierService;
        this.carService = carService;
        this.partService = partService;
        this.saleService = saleService;
        this.fileIO = fileIO;
    }

    @Override
    public void run(String... args) {
        this.seedSuppliers();
        this.seedParts();
        this.seedCars();
        this.seedCustomers();
        this.seedSales();

        this.manager.run();
    }

    private void seedCars() {
        this.carService.seedCars(CARS_PATH);
    }

    private void seedCustomers() {
        this.customerService.seedCustomers(CUSTOMERS_PATH);
    }

    private void seedSuppliers(){
        this.supplierService.seedSuppliers(SUPPLIERS_PATH);
    }

    private void seedParts() {
        this.partService.seedParts(PARTS_PATH);
    }

    private void seedSales() {
        this.saleService.seedSales();
    }
}
