package ua.od.game.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.od.game.dto.NotificationDto;
import ua.od.game.model.NotificationEntity;
import ua.od.game.repository.dao.NotificationDao;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class NotificationServiceImplTest {
    private NotificationServiceImpl nsi;

    @Before
    public void init() {
        nsi = new NotificationServiceImpl();
        nsi.setNotificationDao(() -> Arrays.asList(new NotificationEntity(), new NotificationEntity(), new NotificationEntity()));
    }

    @Test
    public void TestGetAllNotificationList() {
        List<NotificationDto> notificationDtos = nsi.getAllNotificationList();
        Assert.assertNotNull(notificationDtos);
        Assert.assertEquals(notificationDtos.size(), 3);
    }
}