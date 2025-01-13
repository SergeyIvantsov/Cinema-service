package cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "films")
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_year")
    private Integer year;

    @Column
    private String genre;

    @Column
    private String director;

    @ManyToMany (cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH},
            mappedBy = "desiredFilms")
   private Set<Account> accounts =new HashSet<Account>();


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH},
            mappedBy = "watchedFilms")
    private Set<Account> accounts2 = new HashSet<>();
//    @ManyToMany
//    private List<String> actors;
}
