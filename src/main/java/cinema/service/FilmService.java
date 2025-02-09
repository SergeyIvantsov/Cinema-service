package cinema.service;

import cinema.dto.AccountDto;
import cinema.dto.FilmDto;

import java.util.List;

public interface FilmService {

    FilmDto save(FilmDto t);


    FilmDto get(Integer id);


    List<FilmDto> getAll();

    List<FilmDto> getFilmsForPage(int page, int pageSize);

    int getTotalFilmCount();

    List<FilmDto> findByName(String search);

    FilmDto update(Integer id, FilmDto film);


    boolean delete(Integer id);

    List<FilmDto> getFilmsByDirector (Integer directorId);

    void closeDao();


}
