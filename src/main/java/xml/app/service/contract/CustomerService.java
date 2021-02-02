package xml.app.service.contract;


import xml.app.domain.entity.Customer;

public interface CustomerService {

    void seedCustomers(String jsonCustomers);

    int getCount();

    Customer findCustomerById(Integer id);

    Customer getRandomCustomer();

    //Query1
    String getOrderedCustomers();

    String getCustomersTotalSales();

    double round(double value);
}
