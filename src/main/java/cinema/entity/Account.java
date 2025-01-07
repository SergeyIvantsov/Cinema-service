package cinema.entity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "desiredFilms")
@EqualsAndHashCode(exclude = "desiredFilms")
@Builder
@Data
@Table(name = "accounts")
public class Account implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private Integer userId;

//    @Column
//    private List<String> watchedFilms;
//

    @ManyToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "films_for_accounts",
            joinColumns = {@JoinColumn(name="account_id")},
            inverseJoinColumns = {@JoinColumn(name = "film_id")})
    private List<Film> desiredFilms = new ArrayList<Film>();


}
