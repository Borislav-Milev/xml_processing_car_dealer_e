package xml.app.service.contract;

import xml.app.domain.entity.Supplier;

public interface SupplierService {

    void seedSuppliers(String jsonSuppliers);

    Supplier getRandomSupplier();

    Supplier findSupplierById(Integer id);

    String getLocalSuppliers();
}
