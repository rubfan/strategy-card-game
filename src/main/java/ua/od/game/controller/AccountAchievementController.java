package ua.od.game.controller;

import ua.od.game.dto.AccountAchievementDto;

import java.util.List;

public interface AccountAchievementController {
    List<AccountAchievementDto> getAccountAchievementsList(Integer accountId);
}
