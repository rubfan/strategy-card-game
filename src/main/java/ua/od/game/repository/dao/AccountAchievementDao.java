package ua.od.game.repository.dao;

import ua.od.game.dto.AccountAchievementDto;

import java.util.List;

public interface AccountAchievementDao {
    List<AccountAchievementDto> getUserAchievementsList(Integer accountId);
}
