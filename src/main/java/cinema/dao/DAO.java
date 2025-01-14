package cinema.dao;

import cinema.entity.Film;

import java.util.List;

public interface DAO <T>{

    T save(T t);

    T get(Integer id);

    List<T> getAll();

//    List<T> find (String title);

    T update(Integer id, T t);

    boolean delete(Integer id);

    void close();

}
