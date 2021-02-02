package xml.app.domain.model.binding;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedDto {

    @NotNull
    @Length(max = 200)
    @XmlAttribute
    private String name;

    @NotNull
    @XmlAttribute(name = "is-importer")
    private Boolean isImporter;
}
