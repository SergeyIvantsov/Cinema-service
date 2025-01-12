package cinema.utils;


import cinema.dto.AccountDto;
import cinema.dto.FilmDto;
import cinema.dto.UserDto;
import cinema.entity.Account;
import cinema.entity.Film;
import cinema.entity.User;

import java.util.HashSet;
import java.util.Set;

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
                .userName(user.getUserName())
                .build();
    }

    public static User convertUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .userName(userDto.getUserName())
                .build();
    }


    public static AccountDto convertAccount(Account account) {
        Set<Film> desiredFilms = account.getDesiredFilms();
        Set<FilmDto> desiredFilmsDto = new HashSet<>();
        for (Film film : desiredFilms) {
            desiredFilmsDto.add(convertFilm(film));
        }
        return AccountDto.builder()
                .id(account.getId())
                .userDto(convertUser(account.getUser()))
                .desiredFilms(desiredFilmsDto)
                .build();
    }

    public static Account convertAccount(AccountDto accountDto) {
       Set<FilmDto> filmDtoList = accountDto.getDesiredFilms();
        Set<Film> films = new HashSet<>();
       for (FilmDto filmDto : filmDtoList) {
           films.add(convertFilm(filmDto));

       }
        return Account.builder()
                .id(accountDto.getId())
                .user(convertUser(accountDto.getUserDto()))
                .desiredFilms(films)
                .build();
    }

}
