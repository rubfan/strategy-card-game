package ua.od.game;

import org.eclipse.jetty.server.Server;
import ua.od.game.config.AppContextConfig;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationStarter {
    private static final Logger LOG = Logger.getLogger(ApplicationStarter.class.getName());
    public static void main(String[] args) {
        Server jettyServer;
        if (Objects.nonNull(args) && args.length > 0 && args[0].equals("deploy")) {
            DataBaseDeployer.createDb();
            DataBaseDeployer.deployDb();
            jettyServer = new Server(Integer.valueOf(System.getenv("PORT")));
        } else {
            jettyServer = new Server(9090);
        }

        boolean debug = false;
        jettyServer.setHandler(AppContextConfig.getHandlersConfig());
        try {
            jettyServer.start();
            if(debug) jettyServer.dumpStdErr();
            jettyServer.join();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            jettyServer.destroy();
        }
    }
}
