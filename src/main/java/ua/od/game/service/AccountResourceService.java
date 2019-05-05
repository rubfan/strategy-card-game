package ua.od.game.service;

import ua.od.game.dto.AccountResourceDto;

import java.util.List;

public interface AccountResourceService {
    Boolean clearAccountResourceList(Integer accountId);
    List<AccountResourceDto> getAccountResourceList(Integer accountId);
}
