package xml.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xml.app.domain.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //Query1
    @Query("select c from Customer c order by c.birthDate, c.isYoungDriver")
    List<Customer> orderedCustomers();

    //Query5
    @Query("select c from Customer c where c.sales.size > 0")
    List<Customer> customerTotalSales();
}
