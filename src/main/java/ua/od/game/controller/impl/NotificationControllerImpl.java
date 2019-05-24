package ua.od.game.controller.impl;

import ua.od.game.controller.NotificationController;
import ua.od.game.dto.NotificationDto;
import ua.od.game.service.NotificationService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("notifications")
public class NotificationControllerImpl implements NotificationController {

    @Inject
    private NotificationService notificationService;

    @GET
    @Path("all")
    public List<NotificationDto> getAllNotificationList() {
        return notificationService.getAllNotificationList();
    }
}
