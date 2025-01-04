package cinema.utils;


import cinema.dto.FilmDto;
import cinema.entity.Film;

public class ConverterUtil {
    
    public static FilmDto convertCar(Film film) {
        return FilmDto.builder()
                .id(film.getId())
                .title(film.getTitle())
                .description(film.getDescription())
                .year(film.getYear())
//                .length(film.getLength())
                .genre(film.getGenre())
                .director(film.getDirector())
                .build();
    }

   
    public static Film convertCar(FilmDto dto) {
        return Film.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .year(dto.getYear())
//                .length(dto.getLength())
                .genre(dto.getGenre())
                .director(dto.getDirector())
                .build();
    }
}
