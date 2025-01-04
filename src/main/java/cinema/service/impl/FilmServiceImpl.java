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
    public FilmDto save(FilmDto carDTO) {
        Film film = ConverterUtil.convertCar(carDTO);

        carDTO.setId(filmDao.save(film).getId());

        return carDTO;
    }

    @Override
    public FilmDto get(Integer id) {
        return ConverterUtil.convertCar(filmDao.get(id));
    }

    @Override
    public List<FilmDto> getAll() {
        return filmDao.getAll().stream()
                .map(ConverterUtil::convertCar)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDto update(Integer id, FilmDto carDTO) {
        Film film = ConverterUtil.convertCar(carDTO);
        film.setId(id);
        carDTO.setId(filmDao.update(id, film).getId());

        return carDTO;
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
