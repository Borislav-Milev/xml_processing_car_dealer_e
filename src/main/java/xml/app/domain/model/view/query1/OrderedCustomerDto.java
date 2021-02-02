package xml.app.domain.model.view.query1;

import lombok.Setter;

import javax.xml.bind.annotation.*;


@Setter
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomerDto {

    @XmlElement
    private Integer id;

    @XmlElement
    private String name;

    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement(name = "is-young-driver")
    private Boolean isYoungDriver;
}
