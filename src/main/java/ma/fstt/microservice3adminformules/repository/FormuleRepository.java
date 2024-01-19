package ma.fstt.microservice3adminformules.repository;

import ma.fstt.microservice3adminformules.entity.Formule;
import ma.fstt.microservice3adminformules.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormuleRepository extends JpaRepository<Formule, Long> {
    List<Formule> findByProduit(Produit produit);
}
