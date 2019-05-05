package ua.od.game.controller.impl;

import ua.od.game.controller.RoomController;
import ua.od.game.dto.RoomDto;
import ua.od.game.service.RoomService;

import javax.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/room")
public class RoomControllerImpl implements RoomController {
    private static final Logger LOG = Logger.getLogger(RoomControllerImpl.class.getName());

    @Inject
    public RoomService roomService;

    @GET
    @Path("list")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<RoomDto> getAllRoomList() {
        List<RoomDto> roomList = roomService.getAllRoomList();
        LOG.log(Level.INFO, roomList.toString());
        return roomList;
    }
}
