package ua.od.game.service.impl;

import ua.od.game.dto.UserDto;
import ua.od.game.model.UserEntity;
import ua.od.game.repository.dao.UserDao;
import ua.od.game.service.UserService;

import javax.inject.Inject;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Inject
    public UserDao userDao;

    @Override
    public String loginUser(UserDto user) {
        return userDao.loginUser(new UserEntity() {{
            setUsername(user.getUsername());
            setPassword(user.getPassword());
            setToken(UUID.randomUUID().toString());
        }});
    }

    @Override
    public void logoutUser(String token) {
        userDao.logoutUser(token);
    }

    @Override
    public String createNewUser(UserDto user) {
        return userDao.createNewUser(new UserEntity() {{
            setUsername(user.getUsername());
            setPassword(user.getPassword());
            setToken(UUID.randomUUID().toString());
        }});
    }

    @Override
    public UserDto getUserByToken(String token) {
        UserEntity userEntity = userDao.getUserByToken(token);
        return new UserDto() {{
            setId(userEntity.getId());
            setUsername(userEntity.getUsername());
            setPassword(userEntity.getPassword());
            setToken(userEntity.getToken());
        }};
    }

    @Override
    public UserDto getUserById(Integer userId) {
        UserEntity userEntity = userDao.getUserById(userId);
        return new UserDto() {{
            setId(userEntity.getId());
            setUsername(userEntity.getUsername());
            setPassword(userEntity.getPassword());
            setToken(userEntity.getToken());
        }};
    }
}
