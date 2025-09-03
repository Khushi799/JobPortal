package master.dto;

import java.sql.Timestamp;

public class Application {
    private int id;
    private int userId;
    private int jobId;
    private Timestamp appliedOn;
    private String status;

    // No-argument constructor
    public Application() { }

    // Constructor used in your DAO
    public Application(int id, int userId, int jobId, Timestamp appliedOn, String status) {
        this.id = id;
        this.userId = userId;
        this.jobId = jobId;
        this.appliedOn = appliedOn;
        this.status = status;
    }

    // Optional constructor for creating new applications before inserting to DB
    public Application(int userId, int jobId) {
        this.userId = userId;
        this.jobId = jobId;
        this.status = "Pending";
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public Timestamp getAppliedOn() { return appliedOn; }
    public void setAppliedOn(Timestamp appliedOn) { this.appliedOn = appliedOn; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
