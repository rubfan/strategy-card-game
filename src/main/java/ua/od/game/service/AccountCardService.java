package ua.od.game.service;

import java.util.List;

public interface AccountCardService {
    List<Integer> getAllowCardList(Integer accountId);
    Boolean applyCard(Integer userAccountId, Integer enemyAccountId, Integer cardId);
}
