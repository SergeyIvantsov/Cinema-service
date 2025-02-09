package cinema.dao;

import cinema.entity.Account;

public interface AccountDao extends DAO<Account>{

    Account updateWithDiseredFilms(Integer id, Integer t);

    Account updateWithWatchedFilms(Integer id, Integer t);

}
