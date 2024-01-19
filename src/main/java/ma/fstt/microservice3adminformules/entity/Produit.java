package ma.fstt.microservice3adminformules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "produit")

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produit;
    private String titre;
    private String description;
    private String typeProduit ; // "PARTICULIER" ou bien "ENTREPRISE";


    @OneToMany(mappedBy = "produit")
    @JsonIgnore
    Collection<Formule> formules;

}
