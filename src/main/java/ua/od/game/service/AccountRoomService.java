package ua.od.game.service;

import ua.od.game.dto.AccountRoomDto;

import java.util.List;

public interface AccountRoomService {
    List<AccountRoomDto> getAccountRoomList();
    Boolean joinRoom(Integer roomId, Integer accountId);
    Boolean leaveRoom(Integer roomId, Integer accountId);
}
