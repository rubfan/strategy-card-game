package ua.od.game.controller;

import ua.od.game.dto.AccountResourceDto;

import javax.ws.rs.core.Response;
import java.util.List;

public interface AccountResourceController {
    Response clearAccountResourceList(Integer accountId);
    List<AccountResourceDto> getAccountResourceList(Integer accountId);
}
