package ua.od.game.model;

public class ResourceEntity {
    private Integer id;
    private String name;
    private String description;
    private Integer defaultNumber;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDefaultNumber(Integer defaultNumber) {
        this.defaultNumber = defaultNumber;
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

    public Integer getDefaultNumber() {
        return defaultNumber;
    }
}
