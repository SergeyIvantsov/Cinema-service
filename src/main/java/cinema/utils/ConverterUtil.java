package cinema.utils;


import cinema.dto.AccountDto;
import cinema.dto.FilmDto;
import cinema.dto.UserDto;
import cinema.entity.Account;
import cinema.entity.Film;
import cinema.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ConverterUtil {

    public static FilmDto convertFilm(Film film) {
        return FilmDto.builder()
                .id(film.getId())
                .title(film.getTitle())
                .description(film.getDescription())
                .year(film.getYear())
                .genre(film.getGenre())
                .director(film.getDirector())
                .build();
    }


    public static Film convertFilm(FilmDto dto) {
        return Film.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .year(dto.getYear())
                .genre(dto.getGenre())
                .director(dto.getDirector())
                .build();
    }


    public static UserDto convertUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    public static User convertUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .build();
    }


    public static AccountDto convertAccount(Account account) {
        List<Film> desiredFilms = account.getDesiredFilms();
        List<FilmDto> desiredFilmsDto = new ArrayList<>();
        for (Film film : desiredFilms) {
            desiredFilmsDto.add(convertFilm(film));
        }
        return AccountDto.builder()
                .id(account.getId())
                .userId(account.getUserId())
                .desiredFilms(desiredFilmsDto)
                .build();
    }

    public static Account convertAccount(AccountDto accountDto) {
       List<FilmDto> filmDtoList = accountDto.getDesiredFilms();
       List<Film> films = new ArrayList<>();
       for (FilmDto filmDto : filmDtoList) {
           films.add(convertFilm(filmDto));

       }
        return Account.builder()
                .id(accountDto.getId())
                .userId(accountDto.getUserId())
                .desiredFilms(films)
                .build();
    }

}
