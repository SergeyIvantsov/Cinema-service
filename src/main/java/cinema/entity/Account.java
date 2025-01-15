package cinema.entity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"desiredFilms", "user", "watchedFilms"})
@EqualsAndHashCode(exclude = {"desiredFilms", "user", "watchedFilms"})
@Builder
@Data
@Table(name = "accounts")
public class Account implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private User user;

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "films_for_accounts",
            joinColumns = @JoinColumn(name="account_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private Set<Film> desiredFilms = new HashSet<>();


    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "watched_films_for_accounts",
            joinColumns = @JoinColumn(name="account_id"),
            inverseJoinColumns = @JoinColumn(name = "watched_film_id"))
    private Set<Film> watchedFilms=new HashSet<>();

}
