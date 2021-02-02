package xml.app.domain.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "part")
public class PartDto {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;
}
