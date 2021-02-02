package xml.app.domain.model.view.query2;

import lombok.Setter;
import xml.app.domain.model.view.CarDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class ToyotaCarDto extends CarDto {

    @XmlAttribute
    private Integer id;
}
