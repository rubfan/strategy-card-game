package ua.od.game.controller.impl;

import ua.od.game.controller.ResourceController;
import ua.od.game.dto.ResourceDto;
import ua.od.game.service.ResourceService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("resources")
public class ResourceControllerImpl implements ResourceController {

    @Inject
    private ResourceService resourceService;

    @GET
    @Path("all")
    public List<ResourceDto> getAllResourceList() {
        return resourceService.getAllResourceList();
    }
}
