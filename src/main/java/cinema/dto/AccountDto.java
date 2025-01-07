package cinema.dto;
import cinema.entity.Film;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountDto {
    private Integer id;
    private Integer userId;

    private List<Film> desiredFilms;
}
