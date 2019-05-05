package ua.od.game.controller;

import ua.od.game.dto.AccountRoomDto;

import javax.ws.rs.core.Response;
import java.util.List;

public interface AccountRoomController {
    List<AccountRoomDto> getAccountRoomList();
    Response joinRoom(Integer roomId, Integer accountId);
    Response leaveRoom(Integer roomId, Integer accountId);
}
