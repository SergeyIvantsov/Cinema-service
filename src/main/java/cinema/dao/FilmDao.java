package cinema.dao;

import cinema.entity.Film;

import java.util.List;

public interface FilmDao extends DAO<Film>{

    List<Film> getFilmsPage(int offset, int limit);

    List<Film> findByName(String name);

    List<Film> findByDirector(Integer directorId);



}
