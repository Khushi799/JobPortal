package master.dto;

public class Admin {
    private int id;
    private String companyName;
    private String email;
    private String password;
    private String description;

    public Admin() { super(); }

    public Admin(int id, String companyName, String email, String password) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        
    }

    public Admin(String companyName, String email, String password, String description) {
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.description = description;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
