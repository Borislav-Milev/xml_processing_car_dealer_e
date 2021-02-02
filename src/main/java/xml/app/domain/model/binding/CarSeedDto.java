package xml.app.domain.model.binding;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.PositiveOrZero;
import javax.xml.bind.annotation.*;

@Getter
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedDto {

    @NotNull
    @XmlElement
    private String make;

    @NonNull
    @XmlElement
    private String model;

    @NotNull
    @PositiveOrZero
    @XmlElement(name = "travelled-distance")
    private Long travelledDistance;
}
