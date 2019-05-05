package ua.od.game.repository.dao;

import ua.od.game.model.AccountEntity;

public interface AccountDao {
    AccountEntity getAccount(String token);
}
