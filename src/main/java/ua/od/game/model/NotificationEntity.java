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


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
