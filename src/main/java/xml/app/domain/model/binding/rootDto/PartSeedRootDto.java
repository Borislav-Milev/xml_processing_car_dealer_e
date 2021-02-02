package xml.app.domain.model.binding.rootDto;

import lombok.Getter;
import xml.app.domain.model.binding.PartSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartSeedRootDto {

    public PartSeedRootDto() {
        this.partSeedDtos = partSeedDtos = new ArrayList<>();
    }

    @XmlElement(name = "part")
    private List<PartSeedDto> partSeedDtos;
}
