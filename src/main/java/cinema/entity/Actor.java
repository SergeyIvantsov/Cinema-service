package cinema.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "filmsForActors")
@EqualsAndHashCode(exclude = "filmsForActors")
@Builder
@Data
@Table(name = "actors")
public class Actor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "actor_name")
    private String actorName;

    @Column(name = "actor_surname")
    private String actorSurname;


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.PERSIST},fetch = FetchType.LAZY,
            mappedBy = "actors")
    private Set<Film> filmsForActors = new HashSet<>();

}
