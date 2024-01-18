package ma.fstt.microservice3adminformules.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    private String titre1;
    private String description1;
    private String titre2;
    private String description2;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "formules_options")
    private Collection<Option> options;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "formules_avantages")
    private Collection<Option> avantages;


}
