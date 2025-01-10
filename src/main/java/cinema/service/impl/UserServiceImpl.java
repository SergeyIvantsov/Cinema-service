package cinema.service.impl;

import cinema.dao.UserDao;
import cinema.dao.daoImpl.UserDaoImpl;
import cinema.dto.UserDto;
import cinema.entity.User;
import cinema.service.UserService;
import cinema.utils.ConverterUtil;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public UserDto save(UserDto userDto) {
        User user = ConverterUtil.convertUser(userDto);

        userDto.setId(userDao.save(user).getId());

        return userDto;
    }

    @Override
    public UserDto get(Integer id) {
        return ConverterUtil.convertUser(userDao.get(id));
    }

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream()
                .map(ConverterUtil::convertUser)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto update(Integer id, UserDto userDto) {
        User user = ConverterUtil.convertUser(userDto);
        user.setId(id);
        userDto.setId(userDao.update(id, user).getId());

        return userDto;
    }

    @Override
    public boolean delete(Integer id) {
        return userDao.delete(id);
    }

    @Override
    public void closeDao() {
        this.userDao.close();
    }



}
