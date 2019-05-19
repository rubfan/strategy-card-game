package ua.od.game.config;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.eclipse.jetty.util.resource.Resource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import ua.od.game.controller.AccountAchievementController;
import ua.od.game.controller.AccountBuildingController;
import ua.od.game.controller.AccountCardController;
import ua.od.game.controller.AccountController;
import ua.od.game.controller.AccountNotificationController;
import ua.od.game.controller.AccountResourceController;
import ua.od.game.controller.AccountRoomController;
import ua.od.game.controller.AccountUpgradeController;
import ua.od.game.controller.AchievementController;
import ua.od.game.controller.BuildingController;
import ua.od.game.controller.CardController;
import ua.od.game.controller.MessageController;
import ua.od.game.controller.NotificationController;
import ua.od.game.controller.ResourceController;
import ua.od.game.controller.RoomController;
import ua.od.game.controller.UpgradeController;
import ua.od.game.controller.UserController;
import ua.od.game.controller.impl.AccountRoomControllerImpl;
import ua.od.game.controller.impl.RoomControllerImpl;
import ua.od.game.controller.impl.UserControllerImpl;
import ua.od.game.repository.dao.AccountAchievementDao;
import ua.od.game.repository.dao.AccountBuildingDao;
import ua.od.game.repository.dao.AccountCardDao;
import ua.od.game.repository.dao.AccountDao;
import ua.od.game.repository.dao.AccountNotificationDao;
import ua.od.game.repository.dao.AccountResourceDao;
import ua.od.game.repository.dao.AccountRoomDao;
import ua.od.game.repository.dao.AccountUpgradeDao;
import ua.od.game.repository.dao.AchievementDao;
import ua.od.game.repository.dao.BuildingDao;
import ua.od.game.repository.dao.CardDao;
import ua.od.game.repository.dao.MessageDao;
import ua.od.game.repository.dao.NotificationDao;
import ua.od.game.repository.dao.ResourceDao;
import ua.od.game.repository.dao.RoomDao;
import ua.od.game.repository.dao.UpgradeDao;
import ua.od.game.repository.dao.UserDao;
import ua.od.game.repository.dao.impl.AccountRoomDaoImpl;
import ua.od.game.repository.dao.impl.RoomDaoImpl;
import ua.od.game.repository.dao.impl.UserDaoImpl;
import ua.od.game.service.AccountAchievementService;
import ua.od.game.service.AccountBuildingService;
import ua.od.game.service.AccountCardService;
import ua.od.game.service.AccountNotificationService;
import ua.od.game.service.AccountResourceService;
import ua.od.game.service.AccountRoomService;
import ua.od.game.service.AccountService;
import ua.od.game.service.AccountUpgradeService;
import ua.od.game.service.AchievementService;
import ua.od.game.service.BuildingService;
import ua.od.game.service.CardService;
import ua.od.game.service.MessageService;
import ua.od.game.service.NotificationService;
import ua.od.game.service.ResourceService;
import ua.od.game.service.RoomService;
import ua.od.game.service.UpgradeService;
import ua.od.game.service.UserService;
import ua.od.game.service.impl.AccountRoomServiceImpl;
import ua.od.game.service.impl.RoomServiceImpl;
import ua.od.game.service.impl.UserServiceImpl;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class AppContextConfig {
    private static final Set<String> HAVE_AUTH_PAGES = new HashSet<>(Arrays.asList(
            "/rooms.html",
            "/achievements.html",
            "/gameplay.html")
    );

    public static HandlerList getHandlersConfig() {
        HandlerList handlers = new HandlerList();
        handlers.addHandler(getStaticResourceHandler());
        handlers.addHandler(getServletHandler());
        return handlers;
    }

    private static Handler getServletHandler() {
        ServletContextHandler servletsHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        servletsHandler.setContextPath("/");
        servletsHandler.addServlet(new ServletHolder(new ServletContainer(getResourceConfig())), "/rest/*");
        FilterHolder holder = new FilterHolder(new CrossOriginFilter());
        holder.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD,OPTIONS");
        servletsHandler.addFilter(holder, "/rest/*", EnumSet.of(DispatcherType.REQUEST));
        return servletsHandler;
    }

    private static Handler getStaticResourceHandler() {
        ResourceHandler resourceHandler = new ResourceHandler() {
            public void handle(String target, Request baseRequest, HttpServletRequest request,
                               HttpServletResponse response) throws IOException, ServletException {
                if(HAVE_AUTH_PAGES.contains(target)) {
                    Boolean flag = true;
                    for (Cookie cookie : request.getCookies()) {
                        if ("token".equals(cookie.getName())) {
                            flag = false;
                            if (cookie.getValue() == null || cookie.getValue().equals("")) {
                                flag = true;
                            }
                            break;
                        }
                    }
                    if (flag) {
                        response.sendRedirect("/login.html");
                    }
                }
                super.handle(target, baseRequest, request, response);
            }
        };
        resourceHandler.setWelcomeFiles(new String[]{"login.html"});
        resourceHandler.setBaseResource(Resource.newClassPathResource("/webapp"));
        return resourceHandler;
    }

    private static ResourceConfig getResourceConfig() {
        return new ResourceConfig() {{
            packages("ua/od/game");
            register(new AbstractBinder() {
                protected void configure () {
                    bindAsContract(UserControllerImpl.class).to(UserController.class);
                    bindAsContract(RoomControllerImpl.class).to(RoomController.class);
                    bindAsContract(AccountRoomControllerImpl.class).to(AccountRoomController.class);
                    bindAsContract(Object.class).to(AccountAchievementController.class);
                    bindAsContract(Object.class).to(AccountBuildingController.class);
                    bindAsContract(Object.class).to(AccountCardController.class);
                    bindAsContract(Object.class).to(AccountController.class);
                    bindAsContract(Object.class).to(AccountNotificationController.class);
                    bindAsContract(Object.class).to(AccountResourceController.class);
                    bindAsContract(Object.class).to(AccountUpgradeController.class);
                    bindAsContract(Object.class).to(AchievementController.class);
                    bindAsContract(Object.class).to(BuildingController.class);
                    bindAsContract(Object.class).to(CardController.class);
                    bindAsContract(Object.class).to(MessageController.class);
                    bindAsContract(Object.class).to(NotificationController.class);
                    bindAsContract(Object.class).to(ResourceController.class);
                    bindAsContract(Object.class).to(UpgradeController.class);

                    bindAsContract(UserServiceImpl.class).to(UserService.class);
                    bindAsContract(RoomServiceImpl.class).to(RoomService.class);
                    bindAsContract(AccountRoomServiceImpl.class).to(AccountRoomService.class);
                    bindAsContract(Object.class).to(AccountAchievementService.class);
                    bindAsContract(Object.class).to(AccountBuildingService.class);
                    bindAsContract(Object.class).to(AccountCardService.class);
                    bindAsContract(Object.class).to(AccountNotificationService.class);
                    bindAsContract(Object.class).to(AccountResourceService.class);
                    bindAsContract(Object.class).to(AccountService.class);
                    bindAsContract(Object.class).to(AccountUpgradeService.class);
                    bindAsContract(Object.class).to(AchievementService.class);
                    bindAsContract(Object.class).to(BuildingService.class);
                    bindAsContract(Object.class).to(CardService.class);
                    bindAsContract(Object.class).to(MessageService.class);
                    bindAsContract(Object.class).to(NotificationService.class);
                    bindAsContract(Object.class).to(ResourceService.class);
                    bindAsContract(Object.class).to(UpgradeService.class);

                    bindAsContract(UserDaoImpl.class).to(UserDao.class);
                    bindAsContract(RoomDaoImpl.class).to(RoomDao.class);
                    bindAsContract(AccountRoomDaoImpl.class).to(AccountRoomDao.class);
                    bindAsContract(Object.class).to(AccountAchievementDao.class);
                    bindAsContract(Object.class).to(AccountBuildingDao.class);
                    bindAsContract(Object.class).to(AccountCardDao.class);
                    bindAsContract(Object.class).to(AccountDao.class);
                    bindAsContract(Object.class).to(AccountNotificationDao.class);
                    bindAsContract(Object.class).to(AccountResourceDao.class);
                    bindAsContract(Object.class).to(AccountUpgradeDao.class);
                    bindAsContract(Object.class).to(AchievementDao.class);
                    bindAsContract(Object.class).to(BuildingDao.class);
                    bindAsContract(Object.class).to(CardDao.class);
                    bindAsContract(Object.class).to(MessageDao.class);
                    bindAsContract(Object.class).to(NotificationDao.class);
                    bindAsContract(Object.class).to(ResourceDao.class);
                    bindAsContract(Object.class).to(UpgradeDao.class);
                }
            });
        }};
    }
}
