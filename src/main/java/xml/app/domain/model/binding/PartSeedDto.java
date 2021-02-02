package xml.app.domain.model.binding;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.PositiveOrZero;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartSeedDto {

    @NonNull
    @NotNull
    @XmlAttribute
    private String name;

    @NonNull
    @NotNull
    @DecimalMin(value = "0.01")
    @XmlAttribute
    private BigDecimal price;

    @NonNull
    @NotNull
    @PositiveOrZero
    @XmlAttribute
    private Short quantity;
}
