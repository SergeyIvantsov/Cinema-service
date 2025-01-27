package cinema.service.impl;

import cinema.dao.FilmDao;
import cinema.dao.daoImpl.FilmDaoImpl;
import cinema.dto.FilmDto;
import cinema.entity.Film;
import cinema.service.FilmService;
import cinema.utils.ConverterUtil;

import java.util.List;
import java.util.stream.Collectors;

public class FilmServiceImpl implements FilmService {

    private final FilmDao filmDao = new FilmDaoImpl();

    @Override
    public FilmDto save(FilmDto filmDto) {
        Film film = ConverterUtil.convertFilm(filmDto);

        filmDto.setId(filmDao.save(film).getId());

        return filmDto;
    }

    @Override
    public FilmDto get(Integer id) {
        return ConverterUtil.convertFilm(filmDao.get(id));
    }

    @Override
    public List<FilmDto> getAll() {
        return filmDao.getAll().stream()
                .map(ConverterUtil::convertFilm)
                .collect(Collectors.toList());
    }

    @Override
    public List<FilmDto> getFilmsForPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Film> films = filmDao.getFilmsPage(offset, pageSize);
        return films.stream()
                .map(ConverterUtil::convertFilm)
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalFilmCount() {
        return filmDao.getAll().size();
    }



    @Override
    public List<FilmDto> findByName(String search) {
        return filmDao.findByName(search).stream()
                .map(ConverterUtil::convertFilm)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDto update(Integer id, FilmDto filmDto) {
        Film film = ConverterUtil.convertFilm(filmDto);
        film.setId(id);
        filmDto.setId(filmDao.update(id, film).getId());
        return filmDto;
    }

    @Override
    public boolean delete(Integer id) {
        return filmDao.delete(id);
    }

    @Override
    public void closeDao() {
        this.filmDao.close();
    }


}
