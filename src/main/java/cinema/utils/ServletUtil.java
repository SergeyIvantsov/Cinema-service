package cinema.utils;

import cinema.dto.ActorDto;
import cinema.dto.DirectorDto;
import cinema.dto.FilmDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ServletUtil {

    public static FilmDto mapFilm(HttpServletRequest req) {
        return FilmDto.builder()
                .title(getStringParam(req, "title"))
                .description(getStringParam(req, "description"))
                .year(getIntegerParam(req, "year"))
                .genre(getStringParam(req, "genre"))
                .director(getDirectorFromRequest(req))
                .actorsDto(getActorsFromRequest(req))
                .build();
    }

    private static DirectorDto getDirectorFromRequest(HttpServletRequest req) {
        String directorParam = getStringParam(req, "director");
        DirectorDto directorDto = new DirectorDto();
        if (directorParam != null) {
            String[] nameSurname = directorParam.trim().split(" ");
            directorDto.setDirectorName(nameSurname[0]);
            directorDto.setDirectorSurname(nameSurname[1]);
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
