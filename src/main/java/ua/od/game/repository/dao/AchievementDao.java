package ua.od.game.repository.dao;

import ua.od.game.model.AchievementEntity;

import java.util.List;

public interface AchievementDao {
    List<AchievementEntity> getAllAchievementList();
}
