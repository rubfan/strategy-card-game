package ua.od.game.repository.dao.impl;
import ua.od.game.model.NotificationEntity;
import ua.od.game.repository.dao.NotificationDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class NotificationDaoImpl implements NotificationDao {

    private static final String GET_NOTIFICATION_LIST_QUERY = "SELECT * FROM Notification";

    @Override
    public List<NotificationEntity> getAllNotificationList() {
        return SqlHelper.prepareStatement(GET_NOTIFICATION_LIST_QUERY, statement -> {
            ResultSet resultSet = statement.executeQuery();
            List<NotificationEntity> notification = new LinkedList<>();
            while (resultSet.next()) {
                notification.add(new NotificationEntity() {{
                    setId(resultSet.getInt("id"));
                    setName(resultSet.getString("name"));
                    setDescription(resultSet.getString("description"));
                }});
            }
            return notification;
        });
    }
}
