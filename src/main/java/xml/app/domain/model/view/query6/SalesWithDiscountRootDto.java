package xml.app.domain.model.view.query6;

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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sales")
public class SalesWithDiscountRootDto {

    public SalesWithDiscountRootDto() {
        this.saleWithDiscountDtos = new ArrayList<>();
    }

    @XmlElement(name = "sale")
    private List<SaleWithDiscountDto> saleWithDiscountDtos;
}
