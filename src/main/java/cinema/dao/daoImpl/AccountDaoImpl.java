package cinema.dao.daoImpl;

import cinema.dao.AccountDao;
import cinema.entity.Account;
import cinema.utils.ExecutorUtil;
import cinema.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    private final EntityManager entityManager;

    public AccountDaoImpl() {
        this.entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public Account save(Account account) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            em.persist(account);
            return account;
        });
    }

    @Override
    public Account get(Integer id) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> em.find(Account.class, id));
    }

    @Override
    public List<Account> getAll() {
        String query = "FROM " + Account.class.getSimpleName();
        return ExecutorUtil.executeHibernate(this.entityManager,
                em -> em.createQuery(query, Account.class).getResultList());
    }

    @Override
    public Account update(Integer id, Account account) {
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            Account updatedAccount = this.entityManager.find(Account.class, id);
            if (updatedAccount != null) {
                updatedAccount = em.merge(account);
            }

            return updatedAccount;
        });
    }

    @Override
    public boolean delete(Integer id) {

        return Boolean.TRUE.equals(ExecutorUtil.executeHibernate(this.entityManager, em -> {
            Account account = em.find(Account.class, id);
            if (account != null) {
                em.remove(account);
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
