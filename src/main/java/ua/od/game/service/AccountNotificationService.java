package ua.od.game.service;

import ua.od.game.dto.NotificationDto;

import java.util.List;

public interface AccountNotificationService {
    Boolean clearUserNotificationList(Integer accountId);
    List<NotificationDto> getUserRecentNotificationList(Integer accountId);
}
