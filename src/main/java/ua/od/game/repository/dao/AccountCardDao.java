package ua.od.game.repository.dao;

import java.util.List;

public interface AccountCardDao {
    List<Integer> getAllowCardList(Integer accountId);
    Boolean applyCard(Integer userAccountId, Integer enemyAccountId, Integer cardId);
}
