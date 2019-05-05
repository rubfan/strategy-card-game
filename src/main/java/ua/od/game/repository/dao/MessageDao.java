package ua.od.game.repository.dao;

import ua.od.game.model.MessageEntity;

import java.util.Date;
import java.util.List;

public interface MessageDao {
    List<MessageEntity> getMessageList(Integer fromAccountId, Integer toAccountId, Date fromTime);
    Boolean sendMessage(MessageEntity message);
}
