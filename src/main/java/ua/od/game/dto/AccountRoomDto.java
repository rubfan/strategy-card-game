package ua.od.game.dto;

import java.util.Date;

public class AccountRoomDto {
    private Integer id;
    private Integer roomId;
    private AccountDto account1;
    private AccountDto account2;
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

    public AccountDto getAccount1() {
        return account1;
    }

    public void setAccount1(AccountDto account1) {
        this.account1 = account1;
    }

    public AccountDto getAccount2() {
        return account2;
    }

    public void setAccount2(AccountDto account2) {
        this.account2 = account2;
    }

    public Date getStartGameTime() {
        return startGameTime;
    }

    public void setStartGameTime(Date startGameTime) {
        this.startGameTime = startGameTime;
    }
}
