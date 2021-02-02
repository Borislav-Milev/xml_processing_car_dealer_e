package xml.app.domain.model.binding.rootDto;

import lombok.Getter;
import xml.app.domain.model.binding.CarSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedRootDto {

    public CarSeedRootDto() {
        this.carSeedDtos = new ArrayList<>();
    }

    @XmlElement(name = "car")
    private List<CarSeedDto> carSeedDtos;
}
