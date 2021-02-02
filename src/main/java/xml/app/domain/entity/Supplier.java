package xml.app.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;
import xml.app.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @NonNull
    @NotNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @NotNull
    @Column(nullable = false)
    private Boolean isImporter;

    @OneToMany(mappedBy = "supplier")
    private Set<Part> parts = new HashSet<>();
}
