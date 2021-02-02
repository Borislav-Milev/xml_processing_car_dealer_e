package xml.app.domain.model.binding.rootDto;

import lombok.Getter;
import xml.app.domain.model.binding.CustomerSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSeedRootDto {

    public CustomerSeedRootDto() {
        this.customerSeedDtos = new ArrayList<>();
    }

    @XmlElement(name = "customer")
    private List<CustomerSeedDto> customerSeedDtos;
}
