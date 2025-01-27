package cinema.dao;

import java.util.List;

public interface DAO <T>{

    T save(T t);

    T get(Integer id);

    List<T> getAll();

    T update(Integer id, T t);

    boolean delete(Integer id);

    void close();

}
