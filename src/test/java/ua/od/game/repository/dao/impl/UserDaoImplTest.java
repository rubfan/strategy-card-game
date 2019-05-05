package ua.od.game.repository.dao.impl;

import org.junit.Before;
import org.junit.Test;
import ua.od.game.model.UserEntity;
import ua.od.game.repository.dao.DbTest;
import ua.od.game.repository.dao.UserDao;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class UserDaoImplTest extends DbTest {
    UserDao userDao;

    @Before
    public void init() {
        userDao = new UserDaoImpl();
    }

    @Test
    public void testCreateUser() {
        String token,name;
        byte[] array;
        UserEntity user;

        token = UUID.randomUUID().toString();
        array = new byte[7];
        new Random().nextBytes(array);
        name = new String(array, Charset.forName("UTF-8"));
        user = new UserEntity() {{
            setUsername(name);
            setPassword("12345");
            setToken(token);
        }};
        assertEquals(token,userDao.createNewUser(user));
        assertNotEquals(token,userDao.createNewUser(user));
    }

    @Test
    public void testUserLogin() {
        String token;
        UserEntity user;

        token = UUID.randomUUID().toString();
        user = new UserEntity() {{
            setUsername("ramzes");
            setPassword("12345");
            setToken(token);
        }};
        assertEquals(token,userDao.loginUser(user));
        user.setPassword("1234");
        assertNotEquals(token,userDao.loginUser(user));
    }

    @Test
    public void testLogoutUser() {
        String token = "1234";
        assertFalse(userDao.logoutUser(token));
    }

    @Test
    public void testGetUserByToken() {
        String token = "1234";
        assertNull(userDao.getUserByToken(token));
    }

    @Test
    public void testGetUserById() {
        Integer userId = 15;
        assertNull(userDao.getUserById(userId));

    }
}
