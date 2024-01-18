package ma.fstt.microservice3adminformules.repository;

import ma.fstt.microservice3adminformules.entity.Formule;
import ma.fstt.microservice3adminformules.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findAllByFormulesContaining(Formule formule);

    List<Option> findAllByFormulesAvantagesContaining(Formule formule);
}
