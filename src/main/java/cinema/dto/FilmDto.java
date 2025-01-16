package cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilmDto {

    private Integer id;

    private String title;

    private String description;

    private Integer year;

    private String genre;

    private String director;

}
