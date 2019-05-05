package ua.od.game.controller.impl;

import ua.od.game.controller.UserController;
import ua.od.game.dto.UserDto;
import ua.od.game.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/user")
public class UserControllerImpl implements UserController {
    private static final Logger LOG = Logger.getLogger(UserControllerImpl.class.getName());

    @Inject
    public UserService userService;

    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response loginUser(UserDto user) {
        try {
            // Authenticate the user using the credentials provided and issue a token for the user
            String token = userService.loginUser(user);
            // Return the token on the response
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("logout")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response logoutUser(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
        userService.logoutUser(authorization);
        LOG.log(Level.INFO, authorization);
        return Response.ok().build();
    }

    @POST
    @Path("new")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createNewUser(UserDto user) {
        String token = userService.loginUser(user);
        if(token == null) {
            token = userService.createNewUser(user);
        }
        LOG.log(Level.INFO, user.toString());
        return Response.ok(token).build();
    }
}
