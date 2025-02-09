package cinema.dao.daoImpl;

import cinema.dao.ActorDao;
import cinema.entity.Actor;
import cinema.entity.Film;
import cinema.utils.ExecutorUtil;
import cinema.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ActorDaoImpl implements ActorDao {

    private final EntityManager entityManager;

    public ActorDaoImpl() {
        this.entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public List<Actor> findByNameSurname(String firstName, String lastName) {
        String query = "SELECT a FROM Actor a WHERE a.actorName = :name AND a.actorSurname = :surname";
        return ExecutorUtil.executeHibernate(this.entityManager,em ->
             em.createQuery(query, Actor.class).setParameter("name", firstName)
                .setParameter("surname", lastName)
                     .getResultList());
        }

    @Override
    public Actor save(Actor actor) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            em.persist(actor);
            return actor;
        });
    }


    @Override
    public Actor get(Integer id) {
            return ExecutorUtil.executeHibernate(this.entityManager, em -> em.find(Actor.class, id));

    }

    @Override
    public List<Actor> getAll() {
        String query = "FROM " + Actor.class.getSimpleName();
        return ExecutorUtil.executeHibernate(this.entityManager,
                em -> em.createQuery(query, Actor.class).getResultList());
    }

    @Override
    public Actor update(Integer id, Actor actor) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            Actor updatedActor = em.find(Actor.class, id);
            if (updatedActor != null) {
                updatedActor = em.merge(actor);
            }
            return updatedActor;
        });
    }

    @Override
    public boolean delete(Integer id) {
        return Boolean.TRUE.equals(ExecutorUtil.executeHibernate(this.entityManager, em -> {
            Actor actor = em.find(Actor.class, id);
            if (actor != null) {
                em.remove(actor);
                return true;
            } else {
                return false;
            }
        }));    }

    @Override
    public void close() {
        if (this.entityManager.isOpen()) {
            this.entityManager.close();
        }
    }

}
