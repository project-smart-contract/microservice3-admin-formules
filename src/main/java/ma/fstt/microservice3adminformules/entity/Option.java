package ma.fstt.microservice3adminformules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "option")

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_option;

    private String titre;
    private String description;
    private boolean isObligatory;

    @ManyToMany(mappedBy = "options")
    @JsonIgnore
    private List<Formule> formules;

}
