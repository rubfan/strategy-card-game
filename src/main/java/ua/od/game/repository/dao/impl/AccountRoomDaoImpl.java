package ua.od.game.repository.dao.impl;

import ua.od.game.model.AccountRoomEntity;
import ua.od.game.repository.dao.AccountRoomDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class AccountRoomDaoImpl implements AccountRoomDao {
    private static final String GET_STATUS_ROOMS = "SELECT * FROM Account_Room";

    private static final String JOIN_ROOM_QUERY = new StringBuilder()
            .append("UPDATE Account_Room ")
            .append("SET account_1_id = CASE WHEN account_1_id IS NULL ")
            .append("THEN ? ")
            .append("ELSE account_1_id END, ")
            .append("account_2_id = CASE WHEN account_1_id IS NOT NULL AND account_2_id IS NULL AND account_1_id <> ? ")
            .append("THEN ? ")
            .append("ELSE account_2_id END ")
            .append("WHERE room_id = ?")
            .toString();

    private static final String LEAVE_ROOM_QUERY = new StringBuilder()
            .append("UPDATE Account_Room ")
            .append("SET account_1_id = CASE WHEN account_1_id = ? ")
            .append("THEN NULL ")
            .append("ELSE account_1_id END, ")
            .append("account_2_id = CASE WHEN account_2_id = ? ")
            .append("THEN NULL ")
            .append("ELSE account_2_id END ")
            .append("WHERE room_id = ?")
            .toString();

    public List<AccountRoomEntity> getAccountRoomList() {
        return SqlHelper.prepareStatement(GET_STATUS_ROOMS, statement -> {
            ResultSet resultSet = statement.executeQuery();
            List<AccountRoomEntity> rooms = new LinkedList<>();
            while (resultSet.next()) {
                rooms.add(new AccountRoomEntity() {{
                    setId(resultSet.getInt("id"));
                    setRoomId(resultSet.getInt("room_id"));
                }});
            }
            return rooms;
        });
    }

    public Boolean joinRoom(Integer roomId, Integer accountId) {
        return updateRoom(roomId, accountId, JOIN_ROOM_QUERY);
    }

    public Boolean leaveRoom(Integer roomId, Integer accountId) {
        return updateRoom(roomId, accountId, LEAVE_ROOM_QUERY);
    }

    private Boolean updateRoom(Integer roomId, Integer accountId, String query) {
        return SqlHelper.prepareStatement(query, statement -> {
            statement.setInt(1, accountId);
            statement.setInt(2, accountId);
            statement.setInt(3, accountId);
            statement.setInt(4, roomId);
            return statement.executeUpdate() > 0;
        });
    }

}
