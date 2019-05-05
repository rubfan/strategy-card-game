package ua.od.game.repository.dao.impl;

import ua.od.game.model.RoomEntity;
import ua.od.game.repository.dao.RoomDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {
    private static final String GET_ROOM_LIST_QUERY = "SELECT * FROM Room";

    public List<RoomEntity> getAllRoomList() {
        return SqlHelper.prepareStatement(GET_ROOM_LIST_QUERY, statement -> {
            ResultSet resultSet = statement.executeQuery();
            List<RoomEntity> rooms = new LinkedList<>();
            while (resultSet.next()) {
                rooms.add(new RoomEntity() {{
                    setId(resultSet.getInt("id"));
                    setName(resultSet.getString("name"));
                    setDescription(resultSet.getString("description"));
                }});
            }
            return rooms;
        });
    }
}
