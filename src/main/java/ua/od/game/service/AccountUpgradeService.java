package ua.od.game.service;

import ua.od.game.dto.AccountUpgradeDto;

import java.util.List;

public interface AccountUpgradeService {
    Boolean clearAccountUpgradeList(Integer accountId);
    List<AccountUpgradeDto> getAccountUpgradeList(Integer accountId);
}
