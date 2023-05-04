package dk.easv.be;

import java.time.LocalDate;

public class Project {
    private int id;
    private String name;
    private String businessType;
    private String location;
    private LocalDate projectDate;
    private String projectText;

    public Project(int id, String name, String businessType, String location, LocalDate projectDate, String projectText) {
        this.id = id;
        this.name = name;
        this.businessType = businessType;
        this.location = location;
        this.projectDate = projectDate;
        this.projectText = projectText;
    }

    public Project(String name, String businessType, String location, LocalDate projectDate, String projectText) {
        this.name = name;
        this.businessType = businessType;
        this.location = location;
        this.projectDate = projectDate;
        this.projectText = projectText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessTypeType(String businessType) {
        this.businessType = businessType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(LocalDate projectDate) {
        this.projectDate = projectDate;
    }

    public String getProjectText() {
        return projectText;
    }

    public void setProjectText(String projectText) {
        this.projectText = projectText;
    }
}
