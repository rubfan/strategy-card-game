package ua.od.game.repository.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.od.game.dto.ResourceDto;
import ua.od.game.model.ResourceEntity;
import ua.od.game.service.impl.ResourceServiceImpl;

import java.util.Arrays;
import java.util.List;

public class ResourceServiceImplTest {

    private ResourceServiceImpl resourceService;

    @Before
    public void init(){
        resourceService = new ResourceServiceImpl();
        resourceService.setResourceDao(() -> Arrays.asList(new ResourceEntity(), new ResourceEntity(), new ResourceEntity()));
    }

    @Test
    public void getAllResourceListTest(){
        List<ResourceDto> resourceDtos = resourceService.getAllResourceList();
        Assert.assertNotNull(resourceDtos);
        Assert.assertEquals(resourceDtos.size(),3);
    }

}
