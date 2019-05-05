package ua.od.game.repository.dao;

import ua.od.game.model.AccountRoomEntity;

import java.util.List;

public interface AccountRoomDao {
    List<AccountRoomEntity> getAccountRoomList();
    Boolean joinRoom(Integer roomId, Integer accountId);
    Boolean leaveRoom(Integer roomId, Integer accountId);
}
