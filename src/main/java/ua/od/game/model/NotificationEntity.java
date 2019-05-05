package ua.od.game.model;

import java.util.List;

public class NotificationEntity {
    private Integer id;
    private String name;
    private String description;
    private Integer defaultNumber;
    private List<ResourceSetEntity> resourceSetList;
    private List<BuildingSetEntity> buildingSetList;
    private List<UpgradeSetEntity> upgradeSetList;
}
