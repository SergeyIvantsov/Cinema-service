package cinema.dao.daoImpl;

import cinema.dao.UserDao;
import cinema.entity.User;
import cinema.utils.ExecutorUtil;
import cinema.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager;

    public UserDaoImpl() {
        this.entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public User save(User user) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            em.persist(user);
            return user;
        });
    }

    @Override
    public User get(Integer id) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> em.find(User.class, id));
    }

    @Override
    public List<User> getAll() {
        String query = "FROM " + User.class.getSimpleName();
        return ExecutorUtil.executeHibernate(this.entityManager,
                em -> em.createQuery(query, User.class).getResultList());
    }

    @Override
    public User update(Integer id, User user) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            User updatedUser = em.find(User.class, id);
            if (updatedUser != null) {
                updatedUser = em.merge(user);
            }

            return updatedUser;
        });
    }

    @Override
    public boolean delete(Integer id) {

        return Boolean.TRUE.equals(ExecutorUtil.executeHibernate(this.entityManager, em -> {
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
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
