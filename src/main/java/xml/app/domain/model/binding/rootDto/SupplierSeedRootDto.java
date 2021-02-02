package xml.app.domain.model.binding.rootDto;

import lombok.Getter;
import xml.app.domain.model.binding.SupplierSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedRootDto {

    public SupplierSeedRootDto() {
        this.supplierSeedDtos = supplierSeedDtos = new ArrayList<>();
    }

    @XmlElement(name = "supplier")
    private List<SupplierSeedDto> supplierSeedDtos;
}
