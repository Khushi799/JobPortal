package master.dto;

import java.sql.Timestamp;

public class Jobs {

    private int id;
    private String title;
    private String description;
    private String category;
    private String status;
    private String location;
    private Timestamp pdate; // optional, can be null for inserts
    private int postedBy;    // <-- add this field

    // Constructors
    public Jobs() {}

    public Jobs(int id, String title, String description, String category, String status, String location, int postedBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
        this.location = location;
        this.postedBy = postedBy;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getPdate() {
        return pdate;
    }
    public void setPdate(Timestamp pdate) {
        this.pdate = pdate;
    }

    public int getPostedBy() {
        return postedBy;
    }
    public void setPostedBy(int postedBy) {
        this.postedBy = postedBy;
    }
}
