package xml.app.domain.model.view.query6;

import lombok.Getter;
import lombok.Setter;
import xml.app.domain.model.view.CarDto;

import javax.xml.bind.annotation.*;


@Getter
@Setter
@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleWithDiscountDto {

    @XmlElement
    private CarDto car;

    @XmlElement(name = "customer-name")
    private String customerName;

    @XmlElement
    private Double discount;

    @XmlElement
    private double price;

    @XmlElement(name = "price-without-discount")
    private double priceWithoutDiscount;
}
