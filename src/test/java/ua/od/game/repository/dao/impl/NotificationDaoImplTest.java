package ua.od.game.repository.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import ua.od.game.model.NotificationEntity;
import ua.od.game.repository.dao.DbTest;

import java.util.List;

import static org.junit.Assert.*;

public class NotificationDaoImplTest extends DbTest {
    NotificationDaoImpl ndi = new NotificationDaoImpl();

    @Test
    public void TestGetAllNotificationList() {
        List<NotificationEntity> notification = ndi.getAllNotificationList();
        Assert.assertNotNull(notification);
        Assert.assertEquals(notification.size(), 4);
    }
}