package ua.od.game.repository.dao.impl;

import ua.od.game.model.ResourceEntity;
import ua.od.game.repository.dao.ResourceDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class ResourceDaoImpl implements ResourceDao {

    private static final String GET_RESOURCE_LIST_QUERY = "SELECT * FROM resource;";

    @Override
    public List<ResourceEntity> getAllResourceList() {
        return SqlHelper.prepareStatement(GET_RESOURCE_LIST_QUERY, statement -> {
            ResultSet resultSet = statement.executeQuery();
            List<ResourceEntity> resources = new LinkedList<>();
            while (resultSet.next()) {
                resources.add(new ResourceEntity() {{
                    setId(resultSet.getInt("id"));
                    setName(resultSet.getString("name"));
                    setDescription(resultSet.getString("description"));
                }});
            }
            return resources;
        });
    }
}
