package cinema.dao;

import cinema.dto.FilmDto;
import cinema.entity.Film;

import java.util.List;

public interface FilmDao extends DAO<Film>{

    public List<Film> findByName(String name);
}
