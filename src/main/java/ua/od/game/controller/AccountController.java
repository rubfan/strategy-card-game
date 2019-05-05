package ua.od.game.controller;

import ua.od.game.dto.AccountDto;

import javax.ws.rs.core.Cookie;

public interface AccountController {
    AccountDto getAccount(Cookie cookie);
}
