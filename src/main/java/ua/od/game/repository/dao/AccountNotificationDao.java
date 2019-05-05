package ua.od.game.repository.dao;

import ua.od.game.model.NotificationEntity;

import java.util.List;

public interface AccountNotificationDao {
    Boolean clearUserNotificationList(Integer accountId);
    List<NotificationEntity> getUserRecentNotificationList(Integer accountId);
}
