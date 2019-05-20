package ua.od.game.repository.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.od.game.model.ResourceEntity;
import ua.od.game.repository.dao.DbTest;

import java.util.List;

public class ResourceDaoImplTest extends DbTest {
    private ResourceDaoImpl resourceDao;

    @Before
    public void init(){
        resourceDao = new ResourceDaoImpl();
    }

    @Test
    public void getAllResourceListTest() {
        List<ResourceEntity> resources = resourceDao.getAllResourceList();
        Assert.assertNotNull(resources);
        Assert.assertEquals(resources.size(),3);
    }
}
