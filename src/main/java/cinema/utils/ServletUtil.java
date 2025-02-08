package cinema.utils;

import cinema.dto.ActorDto;
import cinema.dto.DirectorDto;
import cinema.dto.FilmDto;
import cinema.entity.Director;
import cinema.service.DirectorService;
import cinema.service.impl.DirectorServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ServletUtil {

    public static FilmDto mapFilm(HttpServletRequest req) {

        FilmDto newFilmDto = FilmDto.builder()
                .title(getStringParam(req, "title"))
                .description(getStringParam(req, "description"))
                .year(getIntegerParam(req, "year"))
                .genre(getStringParam(req, "genre"))
                .director(getDirectorFromRequest(req))
                .actorsDto(getActorsFromRequest(req))
                .build();

        return newFilmDto;
    }


    public static DirectorDto getDirectorFromRequest(HttpServletRequest req) {
        Integer directorId = getIntegerParam(req, "director");

        DirectorDto directorDto = new DirectorDto();
        if (directorId != null) {
            DirectorService directorService = new DirectorServiceImpl();
            DirectorDto directorFromDb = directorService.get(directorId);
            if (directorFromDb != null) {
                directorDto.setId(directorFromDb.getId());
                directorDto.setDirectorName(directorFromDb.getDirectorName());
                directorDto.setDirectorSurname(directorFromDb.getDirectorSurname());
                System.out.println(directorDto.toString());
            } else {
                System.out.println("Такого режиссера нету!!!");
            }
        }
        return directorDto;
    }


    private static Set<ActorDto> getActorsFromRequest(HttpServletRequest req) {
        String actorsParam = getStringParam(req, "actors");
        if (actorsParam != null) {
            return Arrays.stream(actorsParam.split(","))
                    .map(String::trim)
                    .map(ActorDto::new)
                    .collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }
    }


    public static String getStringParam(HttpServletRequest req, String nameField) {
        return Optional.ofNullable(req.getParameter(nameField))
                .filter(StringUtils::isNotBlank)
                .orElse(null);
    }


    public static Integer getIntegerParam(HttpServletRequest req, String nameField) {
        String idStr = req.getParameter(nameField);
        return idStr != null ? Integer.parseInt(idStr) : null;
    }
}
