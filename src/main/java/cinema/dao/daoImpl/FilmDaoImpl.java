package cinema.dao.daoImpl;

import cinema.dao.FilmDao;
import cinema.entity.Director;
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
            Director existingDirector = em.find(Director.class, film.getDirector().getId());

            film.setDirector(existingDirector);  // Устанавливаем привязанный объект

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
        return (ExecutorUtil.executeHibernate(this.entityManager,
                em -> em.createQuery(query, Film.class).getResultList()));

    }

    @Override
    public List<Film> getFilmsPage(int offset, int limit) {
        String query = "FROM " + Film.class.getSimpleName();
        return (ExecutorUtil.executeHibernate(this.entityManager,
                em -> em.createQuery(query, Film.class)
                        .setFirstResult(offset)
                        .setMaxResults(limit)
                        .getResultList()));
    }


    @Override
    public List<Film> findByName(String title) {
        String query = "FROM Film f WHERE f.title LIKE :title";
        return ExecutorUtil.executeHibernate(this.entityManager,
                em -> em.createQuery(query, Film.class)
                        .setParameter("title", "%" + title + "%")
                        .getResultList());
    }

    @Override
    public Film update(Integer id, Film film) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            Film updatedFilm = this.entityManager.find(Film.class, id);
            if (updatedFilm != null) {
                updatedFilm = em.merge(film);
            }
            return updatedFilm;
        });
    }

    @Override
    public boolean delete(Integer id) {

        return Boolean.TRUE.equals(ExecutorUtil.executeHibernate(this.entityManager, em -> {
            Film film = em.find(Film.class, id);
            if (film != null) {
                em.remove(film);
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
