package ua.od.game.repository.dao;

import ua.od.game.model.AccountResourceEntity;

import java.util.List;

public interface AccountResourceDao {
    Boolean clearAccountResourceList(Integer accountId);
    List<AccountResourceEntity> getAccountResourceList(Integer accountId);
}
