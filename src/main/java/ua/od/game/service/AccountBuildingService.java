package ua.od.game.service;

import ua.od.game.dto.AccountBuildingDto;

import java.util.List;

public interface AccountBuildingService {
    List<AccountBuildingDto> getUserBuildingList(Integer accountId);
    Boolean clearUserBuildingList(Integer accountId);
}
