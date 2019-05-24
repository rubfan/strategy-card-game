package ua.od.game.service.impl;

import ua.od.game.dto.NotificationDto;
import ua.od.game.repository.dao.NotificationDao;
import ua.od.game.service.NotificationService;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    public void setNotificationDao(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    @Inject
    public NotificationDao notificationDao;

    public List<NotificationDto> getAllNotificationList() {
        final List<NotificationDto> notification = new LinkedList<>();
        notificationDao.getAllNotificationList().forEach(notificationEntity -> notification.add(new NotificationDto() {{
            setId(notificationEntity.getId());
            setName(notificationEntity.getName());
            setDescription(notificationEntity.getDescription());
        }}));
        return notification;
    }
}
