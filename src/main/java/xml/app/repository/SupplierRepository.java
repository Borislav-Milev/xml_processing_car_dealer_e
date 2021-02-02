package xml.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xml.app.domain.entity.Supplier;
import xml.app.domain.model.view.query3.LocalSupplierDto;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    //Query3
    @Query("select new xml.app.domain.model.view.query3.LocalSupplierDto" +
            "(s.id, s.name, count(p)) from Supplier s join s.parts p where s.isImporter = false group by s.id")
    List<LocalSupplierDto> localSuppliers();
}
