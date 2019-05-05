package ua.od.game.service;

import ua.od.game.dto.AccountDto;

public interface AccountService {
    AccountDto getAccount(String token);
}
