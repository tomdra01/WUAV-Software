package dk.easv.be;

// imports
import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 * @author tomdra01, mrtng1
 */
public class Project {
    private int id;
    private String name;
    private String businessType;
    private String location;
    private LocalDate date;
    private byte[] drawing;
    private String description;
    private boolean approved;

    public Project(int id, String name, String businessType, String location, LocalDate date, byte[] drawing, String description, boolean approved) {
        this.id = id;
        this.name = name;
        this.businessType = businessType;
        this.location = location;
        this.date = date;
        this.drawing = drawing;
        this.description = description;
        this.approved = approved;
    }

    public Project(String name, String businessType, String location, LocalDate date, byte[] drawing, String description, boolean approved) {
        this.name = name;
        this.businessType = businessType;
        this.location = location;
        this.date = date;
        this.drawing = drawing;
        this.description = description;
        this.approved = approved;
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

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public byte[] getDrawing() {
        return drawing;
    }

    public void setDrawing(byte[] drawing) {
        this.drawing = drawing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return name;
    }
}
