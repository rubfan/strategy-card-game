package ua.od.game.repository.dao;

import ua.od.game.model.AccountUpgradeEntity;

import java.util.List;

public interface AccountUpgradeDao {
    Boolean clearAccountUpgradeList(Integer accountId);
    List<AccountUpgradeEntity> getAccountUpgradeList(Integer accountId);
}
