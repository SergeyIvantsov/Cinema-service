package cinema.utils;

import cinema.dto.FilmDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ServletUtil {

    public static FilmDto mapFilm(HttpServletRequest req) {
        return FilmDto.builder()
//                .id(0)
                .title(getStringParam(req, "title"))
                .description(getStringParam(req, "description"))
                .year(getIntegerParam(req, "year"))
                .genre(getStringParam(req, "genre"))
                .director(getStringParam(req, "director"))
                .build();
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
