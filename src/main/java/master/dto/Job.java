package master.dto;

import java.sql.Timestamp;

public class Job {

    private int id;
    private String title;
    private String description;
    private String category;
    private String status;
    private String location;
    private int companyId;       // corresponds to posted_by in DB
    private String salary;
    private Timestamp postedOn;  // corresponds to pdate in DB

    // Default constructor
    public Job() {
        super();
    }

    // Constructor with all fields
    public Job(int id, String title, String description, String category, String status,
               String location, int companyId, String salary, Timestamp postedOn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
        this.location = location;
        this.companyId = companyId;
        this.salary = salary;
        this.postedOn = postedOn;
    }
    
    public Job(int id, String title, String description, int companyId, String location, String salary, Timestamp postedOn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.companyId = companyId;
        this.location = location;
        this.salary = salary;
        this.postedOn = postedOn;
    }

    // Constructor for inserting new jobs (without ID and postedOn)
    public Job(String title, String description, String category, String status,
               String location, int companyId, String salary) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
        this.location = location;
        this.companyId = companyId;
        this.salary = salary;
    }

    
	// Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public Timestamp getPostedOn() { return postedOn; }
    public void setPostedOn(Timestamp postedOn) { this.postedOn = postedOn; }
}
