package ua.od.game.repository.dao.impl;

import ua.od.game.model.UserEntity;
import ua.od.game.repository.dao.UserDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class.getName());

    public String createNewUser(UserEntity user) {
        String token = SqlHelper.prepareStatement("INSERT INTO User(username, password, token) values(?,?,?)", pstmt -> {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getToken());
            return pstmt.executeUpdate();
        });
        if(token.isEmpty()) {
            LOG.warning("This user already exists!!!!");
        }
        return token;
    }

    public String loginUser(UserEntity user) {
        String token = SqlHelper.prepareStatement("UPDATE User Set token = ? where username = ? and password = ?", pstmt -> {
            pstmt.setString(1, user.getToken());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            return pstmt.executeUpdate() > 0 ? user.getToken() : "";
        });
        if(token.isEmpty()) {
            LOG.warning("This user does not exists!!!!");
        }
        return token;
    }

    public boolean logoutUser(String token) {
        boolean logout = SqlHelper.prepareStatement("UPDATE User Set token = '' where token = ?", pstmt -> {
            pstmt.setString(1, token);
            return pstmt.executeUpdate() > 0;
        });
        if(!logout) {
            LOG.warning("This token is wrong!!!!");
        }
        return logout;
    }

    public UserEntity getUserByToken(String token) {
        UserEntity user = SqlHelper.prepareStatement("SELECT * FROM User WHERE token = ?", pstmt -> {
            pstmt.setString(1, token);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? new UserEntity() {{
                setId(rs.getInt("id"));
                setUsername(rs.getString("username"));
                setPassword(rs.getString("password"));
                setToken(rs.getString("token"));
            }} : null;
        });
        if(user == null) {
            LOG.warning("Wrong token!!!!!");
        }
        return user;
    }

    public UserEntity getUserById(Integer userId) {
        UserEntity user = SqlHelper.prepareStatement("SELECT * from User where id = ?", pstmt -> {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? new UserEntity(){{
                setId(rs.getInt("id"));
                setUsername(rs.getString("username"));
                setPassword(rs.getString("password"));
                setToken(rs.getString("token"));
            }} : null;
        });
        if(user == null) {
            LOG.warning("Wrong user Id!!!!!");
        }
        return user;
    }
}
