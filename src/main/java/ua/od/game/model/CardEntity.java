package ua.od.game.model;

import java.util.List;

public class CardEntity {
    private Integer id;
    private String name;
    private String description;
    private CardGroupEntity cardGroup;

    private List<ResourceSetEntity> palayerResourceSetList;
    private List<BuildingSetEntity> palayerBuildingSetList;
    private List<UpgradeSetEntity> palayerUpgradeSetList;

    private List<ResourceSetEntity> enemyResourceSetList;
    private List<BuildingSetEntity> enemyBuildingSetList;
    private List<UpgradeSetEntity> enemyUpgradeSetList;
}
