package cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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

//    private List<String> actors;

}
