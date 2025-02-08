package cinema.service.impl;

import cinema.dao.DirectorDao;
import cinema.dao.daoImpl.DirectorDaoImpl;
import cinema.dto.DirectorDto;
import cinema.entity.Director;
import cinema.service.DirectorService;
import cinema.utils.ConverterUtil;

import java.util.List;
import java.util.stream.Collectors;

public class DirectorServiceImpl implements DirectorService {
    private final DirectorDao directorDao = new DirectorDaoImpl();

    @Override
    public DirectorDto save(DirectorDto directorDto) {
        Director director = ConverterUtil.convertDirector(directorDto);
        directorDto.setId(directorDao.save(director).getId());
        return directorDto;
    }

    @Override
    public DirectorDto get(Integer id) {
        return ConverterUtil.convertDirector(directorDao.get(id));
    }

    @Override
    public List<DirectorDto> getAll() {
        return directorDao.getAll().stream()
                .map(ConverterUtil::convertDirector)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDto update(Integer id, DirectorDto directorDto) {
        Director director = ConverterUtil.convertDirector(directorDto);
        director.setId(id);
        directorDto.setId(directorDao.update(id, director).getId());
        return directorDto;
    }

    @Override
    public boolean delete(Integer id) {
        return directorDao.delete(id);
    }

    @Override
    public void closeDao() {
        this.directorDao.close();
    }


}
