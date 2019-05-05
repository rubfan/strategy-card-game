package ua.od.game.repository.dao;

import ua.od.game.model.NotificationEntity;

import java.util.List;

public interface NotificationDao {
    List<NotificationEntity> getAllNotificationList();
}
