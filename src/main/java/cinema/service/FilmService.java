package cinema.service;

import cinema.dto.FilmDto;

import java.util.List;

public interface FilmService {

    FilmDto save(FilmDto t);


    FilmDto get(Integer id);


    List<FilmDto> getAll();


    FilmDto update(Integer id, FilmDto film);


    boolean delete(Integer id);


    void closeDao();


}
