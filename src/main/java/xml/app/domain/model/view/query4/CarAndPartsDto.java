package xml.app.domain.model.view.query4;

import lombok.Getter;
import lombok.Setter;
import xml.app.domain.model.view.CarDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarAndPartsDto extends CarDto {


    @XmlElement
    private PartRootDto parts;
}
