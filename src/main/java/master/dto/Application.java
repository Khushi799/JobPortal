package master.dto;

import java.sql.Timestamp;

public class Application {
    private int id;
    private int userId;
    private int jobId;
    private String jobTitle;   // fetched from job table
    private String userName;   // fetched from user table
    private Timestamp appliedOn;
    private String status;

    // New fields
    private String email;
    private String qualification;
    private int experience;
    private String coverLetter;

    // No-argument constructor
    public Application() { }

    // Constructor for DB fetch (basic)
    public Application(int id, int userId, int jobId, Timestamp appliedOn, String status) {
        this.id = id;
        this.userId = userId;
        this.jobId = jobId;
        this.appliedOn = appliedOn;
        this.status = status;
    }

    // Constructor for creating new applications
    public Application(int userId, int jobId, String email, String qualification, int experience, String coverLetter) {
        this.userId = userId;
        this.jobId = jobId;
        this.email = email;
        this.qualification = qualification;
        this.experience = experience;
        this.coverLetter = coverLetter;
        this.status = "Pending";
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public Timestamp getAppliedOn() { return appliedOn; }
    public void setAppliedOn(Timestamp appliedOn) { this.appliedOn = appliedOn; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
}
