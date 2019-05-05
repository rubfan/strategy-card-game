package ua.od.game.service;

import ua.od.game.dto.AccountAchievementDto;

import java.util.List;

public interface AccountAchievementService {
    List<AccountAchievementDto> getAccountAchievementsList(Integer accountId);
}
