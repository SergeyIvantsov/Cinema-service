package cinema.service;

import cinema.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto save(UserDto t);


    UserDto get(Integer id);


    List<UserDto> getAll();


    UserDto update(Integer id, UserDto user);


    boolean delete(Integer id);


    void closeDao();




}
