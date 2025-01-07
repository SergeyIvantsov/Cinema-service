package cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @ManyToMany (mappedBy = "desiredFilms")
    private List<Account> accounts =new ArrayList<>();

//    @ManyToMany
//    private List<String> actors;
}
