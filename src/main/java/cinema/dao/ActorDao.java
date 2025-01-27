package cinema.dao;

import cinema.entity.Actor;

import java.util.List;

public interface ActorDao extends DAO<Actor> {

    public List<Actor> findByNameSurname(String name, String surname);

    public Actor save(Actor actor);

}
