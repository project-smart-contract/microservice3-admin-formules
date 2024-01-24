package ma.fstt.microservice3adminformules.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "formule")

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Formule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_formule;

    private String titre;
    private String description;
    private String images;
    private boolean isNew;
    private String slug;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "formules_options")
    private Collection<Option> options;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "formules_avantages")
    private Collection<Option> avantages;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;


    public List<String> getImagesList() {
        return Arrays.asList(this.images.split(","));
    }

    public void setImagesList(List<String> images) {
        this.images = String.join(",", images);
    }


}
