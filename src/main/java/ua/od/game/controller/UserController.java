package ua.od.game.controller;

import ua.od.game.dto.UserDto;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;

public interface UserController {
    Response loginUser(UserDto user);
    Response logoutUser(String authorization);
    Response createNewUser(UserDto user);
}
