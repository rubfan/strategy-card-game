package ua.od.game.controller;

import ua.od.game.dto.AccountUpgradeDto;

import javax.ws.rs.core.Response;
import java.util.List;

public interface AccountUpgradeController {
    Response clearAccountUpgradeList(Integer accountId);
    List<AccountUpgradeDto> getAccountUpgradeList(Integer accountId);
}
