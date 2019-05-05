package ua.od.game.controller;

import ua.od.game.dto.MessageDto;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

public interface MessageController {
    List<MessageDto> getMessageList(Integer fromAccountId, Integer toAccountId, Date fromTime);
    Response sendMessage(MessageDto message);
}
