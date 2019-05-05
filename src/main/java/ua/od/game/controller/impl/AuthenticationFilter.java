package ua.od.game.controller.impl;

import ua.od.game.dto.UserDto;
import ua.od.game.service.UserService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    private static final Set<String> HAVE_AUTH_PAGES = new HashSet<>(Arrays.asList(
            "/rooms.html",
            "/achievements.html",
            "/gameplay.html")
    );
    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer ";

    @Inject
    private UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Get the Authorization header/cookie from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            validateToken(token);
        } catch (Exception e) {
            if (HAVE_AUTH_PAGES.contains(requestContext.getUriInfo().getPath())) {
                requestContext.setRequestUri(URI.create("/login.html"));
            }
            abortWithUnauthorized(requestContext);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {
        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase());
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                .build());
    }

    private void validateToken(String token) throws Exception {
        // Get User by Token
        UserDto user = userService.getUserByToken(token);
        // Check if the token was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
        if (user == null) {
            throw new Exception();
        }
    }
}