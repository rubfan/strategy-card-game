package ua.od.game.model;

import java.util.List;

public class AchievementEntity {
    private Integer id;
    private String name;
    private String description;
    private List<ResourceSetEntity> resourceSetList;
    private List<BuildingSetEntity> buildingSetList;
    private List<UpgradeSetEntity> upgradeSetList;
}
