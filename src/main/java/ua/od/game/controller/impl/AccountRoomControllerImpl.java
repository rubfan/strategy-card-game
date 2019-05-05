package ua.od.game.controller.impl;

import ua.od.game.controller.AccountRoomController;
import ua.od.game.dto.AccountRoomDto;
import ua.od.game.service.AccountRoomService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/room")
public class AccountRoomControllerImpl implements AccountRoomController {
    private static final Logger LOG = Logger.getLogger(AccountRoomControllerImpl.class.getName());

    @Inject
    public AccountRoomService accountRoomService;

    @GET
    @Path("account/list")
    public List<AccountRoomDto> getAccountRoomList() {
        return accountRoomService.getAccountRoomList();
    }

    @GET
    @Path("{roomId}/account/{accountId}/join")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response joinRoom(@PathParam("roomId") Integer roomId, @PathParam("accountId") Integer accountId) {
        LOG.log(Level.INFO, String.format("room: %s, account: %s", roomId, accountId));
        accountRoomService.joinRoom(roomId, accountId);
        return Response.ok().entity("User Entered Room").build();
    }

    @GET
    @Path("{roomId}/account/{accountId}/leave")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response leaveRoom(@PathParam("roomId") Integer roomId, @PathParam("accountId") Integer accountId) {
        LOG.log(Level.INFO, String.format("room: %s, account: %s", roomId, accountId));
        accountRoomService.leaveRoom(roomId, accountId);
        return Response.ok().entity("User Left Room").build();
    }
}
