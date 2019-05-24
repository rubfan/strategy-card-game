package ua.od.game.dto;

import java.util.List;

public class NotificationDto {
    private Integer id;
    private String name;
    private String description;
    private Integer defaultNumber;
    private List<ResourceSetDto> resourceSetList;
    private List<BuildingSetDto> buildingSetList;
    private List<UpgradeSetDto> upgradeSetList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
