package cinema.dao.daoImpl;

import cinema.dao.DirectorDao;
import cinema.entity.Director;
import cinema.utils.ExecutorUtil;
import cinema.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class DirectorDaoImpl implements DirectorDao {

    private final EntityManager entityManager;

    public DirectorDaoImpl() {
        this.entityManager = HibernateUtil.getEntityManager();
    }


    @Override
    public Director save(Director Director) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            em.persist(Director);
            return Director;
        });
    }


    @Override
    public Director get(Integer id) {
        return ExecutorUtil.executeHibernate(this.entityManager,
                em -> em.find(Director.class, id));
    }

    @Override
    public List<Director> getAll() {
        String query = "FROM " + Director.class.getSimpleName();
        return ExecutorUtil.executeHibernate(this.entityManager,
                em -> em.createQuery(query, Director.class).getResultList());
    }

    @Override
    public Director update(Integer id, Director Director) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            Director updatedDirector = em.find(Director.class, id);
            if (updatedDirector != null) {
                updatedDirector = em.merge(Director);
            }
            return updatedDirector;
        });
    }

    @Override
    public boolean delete(Integer id) {
        return Boolean.TRUE.equals(ExecutorUtil.executeHibernate(this.entityManager, em -> {
            Director Director = em.find(Director.class, id);
            if (Director != null) {
                em.remove(Director);
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
