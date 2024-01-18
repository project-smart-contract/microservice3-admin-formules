package ma.fstt.microservice3adminformules.repository;

import ma.fstt.microservice3adminformules.entity.Formule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormuleRepository extends JpaRepository<Formule, Long> {
}
