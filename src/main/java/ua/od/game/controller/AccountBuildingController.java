package ua.od.game.controller;

import ua.od.game.dto.AccountBuildingDto;

import javax.ws.rs.core.Response;
import java.util.List;

public interface AccountBuildingController {
    List<AccountBuildingDto> getAccountBuildingList(Integer accountId);
    Response clearAccountBuildingList(Integer accountId);
}
