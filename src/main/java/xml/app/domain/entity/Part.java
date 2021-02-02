package xml.app.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;
import xml.app.domain.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @NotNull
    @Column(nullable = false, columnDefinition = "DECIMAL(8,2)")
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @NonNull
    @NotNull
    @Column(nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    @PositiveOrZero
    private Short quantity;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Supplier supplier;
}
