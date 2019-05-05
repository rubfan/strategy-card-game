package ua.od.game.model;

import java.util.Date;

public class AccountRoomEntity {
    private Integer id;
    private Integer roomId;
    private AccountEntity account1;
    private AccountEntity account2;
    private Date startGameTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public AccountEntity getAccount1() {
        return account1;
    }

    public void setAccount1(AccountEntity account1) {
        this.account1 = account1;
    }

    public AccountEntity getAccount2() {
        return account2;
    }

    public void setAccount2(AccountEntity account2) {
        this.account2 = account2;
    }

    public Date getStartGameTime() {
        return startGameTime;
    }

    public void setStartGameTime(Date startGameTime) {
        this.startGameTime = startGameTime;
    }
}
