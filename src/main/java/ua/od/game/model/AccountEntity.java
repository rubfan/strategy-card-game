package ua.od.game.model;

public class AccountEntity {
    private Integer userId;
    private Float rank;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getRank() {
        return rank;
    }

    public void setRank(Float rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "userId=" + userId +
                ", rank=" + rank +
                '}';
    }
}
