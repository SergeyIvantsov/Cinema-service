package cinema.dto;

import lombok.*;

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

    private String directorName;

    private DirectorDto director;

    private Set<ActorDto> actorsDto = new HashSet<>();

}
