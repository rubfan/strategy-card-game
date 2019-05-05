package ua.od.game.dto;

import java.util.List;

public class CardDto {
    private Integer id;
    private String name;
    private String description;
    private CardGroupDto cardGroup;

    private List<ResourceSetDto> palayerResourceSetList;
    private List<BuildingSetDto> palayerBuildingSetList;
    private List<UpgradeSetDto> palayerUpgradeSetList;

    private List<ResourceSetDto> enemyResourceSetList;
    private List<BuildingSetDto> enemyBuildingSetList;
    private List<UpgradeSetDto> enemyUpgradeSetList;
}
