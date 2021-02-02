package xml.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xml.app.domain.entity.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {


    //Query2
    @Query("select c from Car c where c.make like 'Toyota' order by c.model, c.travelledDistance desc")
    List<Car> toyotaCars();

    @Query("select c from Car c")
    List<Car> carsAndParts();
}
