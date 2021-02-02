package xml.app.domain.model.binding;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.xml.bind.annotation.*;

@Getter
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSeedDto {

    @NotNull
    @XmlAttribute
    private String name;

    @NotNull
    @XmlElement(name = "birth-date")
    private String birthDate;

    @NotNull
    @XmlElement(name = "is-young-driver")
    private Boolean isYoungDriver;
}
