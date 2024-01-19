package ma.fstt.microservice3adminformules.repository;

import ma.fstt.microservice3adminformules.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {


}
