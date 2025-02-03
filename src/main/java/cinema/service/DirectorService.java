package cinema.service;

import cinema.dto.DirectorDto;

import java.util.List;

public interface DirectorService {

    DirectorDto save(DirectorDto t);


    DirectorDto get(Integer id);


    List<DirectorDto> getAll();


    DirectorDto update(Integer id, DirectorDto Director);


    boolean delete(Integer id);


    void closeDao();



}
