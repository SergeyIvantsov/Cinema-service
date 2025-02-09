package cinema.service.impl;

import cinema.dao.AccountDao;
import cinema.dao.daoImpl.AccountDaoImpl;
import cinema.dto.AccountDto;
import cinema.dto.FilmDto;
import cinema.entity.Account;
import cinema.service.AccountService;
import cinema.service.FilmService;
import cinema.utils.Constants;
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
        Integer id = accountDao.save(account).getId();
        accountDto.setId(id);
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
    public AccountDto updateAfterDeleteFilm (Integer id, AccountDto accountDto) {
        Account account = ConverterUtil.convertAccount(accountDto);
        account.setId(id);
        accountDto.setId(accountDao.update(id, account).getId());
        return accountDto;
    }

    @Override
    public AccountDto update(Integer accountId, Integer filmId, boolean isDisered) {
        Account accountUpdate;
        if (isDisered){
            accountUpdate = accountDao.updateWithDiseredFilms(accountId, filmId);
        }else{
           accountUpdate = accountDao.updateWithWatchedFilms(accountId, filmId);
        }
        AccountDto accountDto = ConverterUtil.convertAccount(accountUpdate);
        return accountDto;
    }


    @Override
    public boolean delete(Integer id) {
        return accountDao.delete(id);
    }

    @Override
    public void deleteDiseredFilmFromAccount(Integer idFilm) {
        AccountDto accountDto = get(Constants.ACCOUNT_ID);
        Set<FilmDto> desiredFilms = accountDto.getDesiredFilms();
        FilmDto filmDto = filmService.get(idFilm);
        desiredFilms.remove(filmDto);
        updateAfterDeleteFilm(Constants.ACCOUNT_ID, accountDto);
    }


    @Override
    public void addFilmToDesireList(Integer accountId, Integer filmId) {
        update(accountId, filmId, true);
    }

    @Override
    public void addFilmToWatchedList(Integer accountId, Integer filmId) {
        update(accountId, filmId, false);
    }


    @Override
    public void deleteWatchedFilmFromAccount(Integer idFilm) {
        AccountDto accountDto = get(Constants.ACCOUNT_ID);
        Set<FilmDto> watchedFilms = accountDto.getWatchedFilms();
        FilmDto filmDto = filmService.get(idFilm);
        watchedFilms.remove(filmDto);
        updateAfterDeleteWatchedFilm(Constants.ACCOUNT_ID, accountDto);
    }


    @Override
    public AccountDto updateAfterDeleteWatchedFilm (Integer id, AccountDto accountDto) {
        Account account = ConverterUtil.convertAccount(accountDto);
        account.setId(id);
        accountDto.setId(accountDao.update(id, account).getId());
        return accountDto;
    }


    @Override
    public void closeDao() {
        this.accountDao.close();
    }


}
