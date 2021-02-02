package xml.app.domain.model.view.query5;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerTotalSalesRootDto {

    public CustomerTotalSalesRootDto() {
        this.customerTotalSalesDtos = new ArrayList<>();
    }

    @XmlElement(name = "customer")
    private List<CustomerTotalSalesDto> customerTotalSalesDtos;
}
