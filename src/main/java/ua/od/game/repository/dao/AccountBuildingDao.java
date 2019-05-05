package ua.od.game.repository.dao;

import ua.od.game.model.AccountBuildingEntity;

import java.util.List;

public interface AccountBuildingDao {
    List<AccountBuildingEntity> getAccountBuildingList(Integer accountId);
    Boolean clearAccountBuildingList(Integer accountId);
}
