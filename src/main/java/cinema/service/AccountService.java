package cinema.service;

import cinema.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto save(AccountDto t);


    AccountDto get(Integer id);


    List<AccountDto> getAll();

    AccountDto update(Integer id, AccountDto account);

    boolean delete(Integer id);

    void deleteFilmFromAccount (Integer idFilm);


    void closeDao();


}
