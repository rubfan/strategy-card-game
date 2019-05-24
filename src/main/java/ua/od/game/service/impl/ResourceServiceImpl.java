package ua.od.game.service.impl;

import ua.od.game.dto.ResourceDto;
import ua.od.game.repository.dao.ResourceDao;
import ua.od.game.service.ResourceService;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

public class ResourceServiceImpl implements ResourceService {

    @Inject
    public ResourceDao resourceDao;

    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    @Override
    public List<ResourceDto> getAllResourceList() {
        final List<ResourceDto> resourceDtos = new LinkedList<>();
        resourceDao.getAllResourceList().forEach(resourceEntity -> resourceDtos.add(new ResourceDto() {{
            setId(resourceEntity.getId());
            setName(resourceEntity.getName());
            setDescription(resourceEntity.getDescription());
        }}));
        return resourceDtos;
    }
}
