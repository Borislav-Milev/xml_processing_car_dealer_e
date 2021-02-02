package xml.app.domain.model.view.query1;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomerRootDto {

    public OrderedCustomerRootDto() {
        this.orderedCustomerDtos = new ArrayList<>();
    }

    @XmlElement(name = "customer")
    private List<OrderedCustomerDto> orderedCustomerDtos;
}
