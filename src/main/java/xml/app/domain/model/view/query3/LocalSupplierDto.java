package xml.app.domain.model.view.query3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSupplierDto {

    @XmlAttribute
    private Integer id;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private Long partsCount;
}
