package xml.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xml.app.domain.entity.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {
}
