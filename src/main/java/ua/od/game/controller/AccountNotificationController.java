package ua.od.game.controller;

import ua.od.game.dto.NotificationDto;

import javax.ws.rs.core.Response;
import java.util.List;

public interface AccountNotificationController {
    Response clearAccountNotificationList(Integer accountId);
    List<NotificationDto> getAccountRecentNotificationList(Integer accountId);
}
