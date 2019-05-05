package ua.od.game.repository.dao;

import ua.od.game.DataBaseDeployer;
import ua.od.game.config.DataBaseConfig;

public class DbTest {
    static {
        DataBaseConfig.DB_DATABASE_URL = DataBaseConfig.DB_HOST;
        DataBaseDeployer.deployDb();
    }
}
