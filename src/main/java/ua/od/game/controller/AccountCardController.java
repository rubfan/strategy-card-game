package ua.od.game.controller;

import javax.ws.rs.core.Response;
import java.util.List;

public interface AccountCardController {
    List<Integer> getAllowCardList(Integer accountId);
    Response applyCard(Integer userAccountId, Integer enemyAccountId, Integer cardId);
}
