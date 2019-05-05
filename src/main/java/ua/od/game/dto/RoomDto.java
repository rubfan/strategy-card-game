package ua.od.game.dto;

public class RoomDto {
    private Integer id;
    private String name;
    private String description;
    private Float minRank;

    public RoomDto() {}

    public RoomDto(Integer id, String name, String description, Float minRank) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.minRank = minRank;
    }

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

    public Float getMinRank() {
        return minRank;
    }

    public void setMinRank(Float minRank) {
        this.minRank = minRank;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", minRank='" + minRank + '\'' +
                '}';
    }
}
