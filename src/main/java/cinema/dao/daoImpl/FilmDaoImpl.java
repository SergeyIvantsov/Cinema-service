package cinema.dao.daoImpl;

import cinema.dao.FilmDao;
import cinema.entity.Film;
import cinema.utils.ExecutorUtil;
import cinema.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class FilmDaoImpl implements FilmDao {


    private final EntityManager entityManager;

    public FilmDaoImpl() {
        this.entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public Film save(Film film) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            em.persist(film);
            return film;
        });
    }

    @Override
    public Film get(Integer id) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> em.find(Film.class, id));
    }

    @Override
    public List<Film> getAll() {
        String query = "FROM " + Film.class.getSimpleName();
        return ExecutorUtil.executeHibernate(this.entityManager,
                em -> em.createQuery(query, Film.class).getResultList());
    }

    @Override
    public Film update(Integer id, Film film) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            Film updatedCar = this.entityManager.find(Film.class, id);
            if (updatedCar != null) {
                updatedCar = em.merge(film);
            }

            return updatedCar;
        });
    }

    @Override
    public boolean delete(Integer id) {

        return Boolean.TRUE.equals(ExecutorUtil.executeHibernate(this.entityManager, em -> {
            Film car = em.find(Film.class, id);
            if (car != null) {
                em.remove(car);
                return true;
            } else {
                return false;
            }
        }));
    }

    @Override
    public void close() {
        if (this.entityManager.isOpen()) {
            this.entityManager.close();
        }
    }
}
