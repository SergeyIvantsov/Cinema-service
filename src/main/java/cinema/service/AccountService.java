package cinema.service;

import cinema.dto.AccountDto;
import cinema.dto.FilmDto;

import java.util.List;

public interface AccountService {

    AccountDto save(AccountDto t);

    AccountDto get(Integer id);

    List<AccountDto> getAll();

    AccountDto update(Integer accountId, Integer filmId, boolean is);

    AccountDto updateAfterDeleteFilm (Integer accountId, AccountDto accountDto);

    AccountDto updateAfterDeleteWatchedFilm (Integer accountId, AccountDto accountDto);

    void addFilmToDesireList(Integer accountId, Integer filmId);

    void addFilmToWatchedList(Integer accountId, Integer filmId);

    boolean delete(Integer id);

    void deleteDiseredFilmFromAccount(Integer idFilm);

    void deleteWatchedFilmFromAccount(Integer idWatchFilm);

    void closeDao();


}
