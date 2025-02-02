package cinema.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString (exclude = "filmsByDirector")
@EqualsAndHashCode (exclude = "filmsByDirector")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "directors")

public class Director implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_director")
    private Integer id;

    @Column
    private String directorName;

    @Column
    private String directorSurname;

    @OneToMany (mappedBy = "director")
    private Set<Film> filmsByDirector = new HashSet<>();

}
