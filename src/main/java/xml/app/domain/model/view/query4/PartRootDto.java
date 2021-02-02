package xml.app.domain.model.view.query4;

import lombok.Getter;
import lombok.Setter;
import xml.app.domain.model.view.PartDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "parts")
public class PartRootDto {

    public PartRootDto() {
        this.parts = new ArrayList<>();
    }

    @XmlElement(name = "part")
    private List<PartDto> parts;
}
