package ua.od.game.controller;

import ua.od.game.dto.NotificationDto;

import java.util.List;

public interface NotificationController {
    List<NotificationDto> getAllNotificationList();
}
