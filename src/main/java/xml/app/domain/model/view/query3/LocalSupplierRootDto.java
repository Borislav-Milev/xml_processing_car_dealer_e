package xml.app.domain.model.view.query3;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSupplierRootDto {

    public LocalSupplierRootDto() {
        this.localSupplierDtos = new ArrayList<>();
    }

    @XmlElement(name = "supplier")
    private List<LocalSupplierDto> localSupplierDtos;
}
