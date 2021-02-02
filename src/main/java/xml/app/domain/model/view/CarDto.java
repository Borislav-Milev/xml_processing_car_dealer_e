package xml.app.domain.model.view;

import lombok.Getter;
import lombok.Setter;
import xml.app.domain.model.view.query2.ToyotaCarDto;
import xml.app.domain.model.view.query4.CarAndPartsDto;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ToyotaCarDto.class, CarAndPartsDto.class})
public class CarDto {

    @XmlAttribute
    private String make;

    @XmlAttribute
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;
}
