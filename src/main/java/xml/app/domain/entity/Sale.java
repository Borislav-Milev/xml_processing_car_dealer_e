package xml.app.domain.entity;

import lombok.*;
import xml.app.domain.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @NonNull
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Car car;

    @NonNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Customer customer;

    @PositiveOrZero
    private Double discount;
}
