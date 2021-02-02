package xml.app.domain.model.view.query5;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customer")
public class CustomerTotalSalesDto {

    @XmlAttribute(name = "full-name")
    private String name;

    @XmlAttribute(name = "bought-cars")
    private Integer boughtCars;

    @XmlAttribute(name = "spent-money")
    private double spentMoney;
}
