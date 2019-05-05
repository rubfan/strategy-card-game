package ua.od.game.service.impl;

import ua.od.game.dto.AccountDto;
import ua.od.game.dto.AccountRoomDto;
import ua.od.game.repository.dao.AccountRoomDao;
import ua.od.game.service.AccountRoomService;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class AccountRoomServiceImpl implements AccountRoomService {

    @Inject
    public AccountRoomDao accountRoomDao;

    public List<AccountRoomDto> getAccountRoomList() {
        final List<AccountRoomDto> rooms = new LinkedList<>();
        accountRoomDao.getAccountRoomList().forEach(accRoomEntity -> rooms.add(new AccountRoomDto() {{
            setId(accRoomEntity.getId());
            setRoomId(accRoomEntity.getRoomId());
            setStartGameTime(accRoomEntity.getStartGameTime());
            if(Objects.nonNull(accRoomEntity.getAccount1())) {
                setAccount1(new AccountDto());
                getAccount1().setRank(accRoomEntity.getAccount1().getRank());
                getAccount1().setUserId(accRoomEntity.getAccount1().getUserId());
            }
            if(Objects.nonNull(accRoomEntity.getAccount2())) {
                setAccount2(new AccountDto());
                getAccount2().setRank(accRoomEntity.getAccount2().getRank());
                getAccount2().setUserId(accRoomEntity.getAccount2().getUserId());
            }
        }}));
        return rooms;
    }

    public Boolean joinRoom(Integer roomId, Integer accountId) {
        return accountRoomDao.joinRoom(roomId, accountId);
    }

    public Boolean leaveRoom(Integer roomId, Integer accountId) {
        return accountRoomDao.leaveRoom(roomId, accountId);
    }
}
