package cinema.service.impl;

import cinema.dao.AccountDao;
import cinema.dao.daoImpl.AccountDaoImpl;
import cinema.dto.AccountDto;
import cinema.dto.FilmDto;
import cinema.entity.Account;
import cinema.service.AccountService;
import cinema.service.FilmService;
import cinema.utils.ConverterUtil;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao = new AccountDaoImpl();
    private final FilmService filmService = new FilmServiceImpl();

    @Override
    public AccountDto save(AccountDto accountDto) {
        Account account = ConverterUtil.convertAccount(accountDto);
        accountDto.setId(accountDao.save(account).getId());
        return accountDto;
    }

    @Override
    public AccountDto get(Integer id) {
        return ConverterUtil.convertAccount(accountDao.get(id));
    }

    @Override
    public List<AccountDto> getAll() {
        return accountDao.getAll().stream()
                .map(ConverterUtil::convertAccount)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto update(Integer id, AccountDto accountDto) {
        Account account = ConverterUtil.convertAccount(accountDto);
        account.setId(id);
        accountDto.setId(accountDao.update(id, account).getId());
        return accountDto;
    }

    @Override
    public boolean delete(Integer id) {
        return accountDao.delete(id);
    }

    @Override
    public void deleteFilmFromAccount(Integer idFilm) {
        AccountDto accountDto = get(1);//toDo хардкод
        Set<FilmDto> desiredFilms = accountDto.getDesiredFilms();
        FilmDto filmDto = filmService.get(idFilm);
        desiredFilms.remove(filmDto);
        update(1, accountDto);
    }



    @Override
    public void addFilmToDesireList(Integer accountId, Integer filmId) {
    AccountDto accountDto = get(accountId);
    FilmDto filmDto = filmService.get(filmId);
    accountDto.getDesiredFilms().add(filmDto);
    update(accountId, accountDto);
    }

    @Override
    public void addFilmToWatchedList(Integer accountId, Integer filmId) {
        AccountDto accountDto = get(accountId);
        FilmDto filmDto = filmService.get(filmId);
        accountDto.getWatchedFilms().add(filmDto);
        update(accountId, accountDto);
    }


    @Override
    public void deleteWatchedFilmFromAccount(Integer idFilm) {
        AccountDto accountDto = get(1);//toDo хардкод
        Set<FilmDto> watchedFilms = accountDto.getWatchedFilms();
        FilmDto filmDto = filmService.get(idFilm);
        watchedFilms.remove(filmDto);
        update(1, accountDto);
    }



    @Override
    public void closeDao() {
        this.accountDao.close();
    }


}
