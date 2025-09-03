package master.dao;

import master.dto.Application;
import master.utilities.ConnectionFactory; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    // 1. Get all applications for a specific job
    public List<Application> getApplicationsByJob(int jobId) {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE job_id=?";

        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jobId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Application app = new Application(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("job_id"),
                        rs.getTimestamp("applied_on"),
                        rs.getString("status")
                    );
                    list.add(app);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 2. Add a new application
    public boolean addApplication(Application app) {
        String sql = "INSERT INTO applications (user_id, job_id, applied_on, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, app.getUserId());
            ps.setInt(2, app.getJobId());
            ps.setTimestamp(3, app.getAppliedOn() != null ? app.getAppliedOn() : new Timestamp(System.currentTimeMillis()));
            ps.setString(4, app.getStatus() != null ? app.getStatus() : "Pending");

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Update application status
    public boolean updateApplicationStatus(int applicationId, String status) {
        String sql = "UPDATE applications SET status=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, applicationId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Optional: Get application by ID
    public Application getApplicationById(int id) {
        String sql = "SELECT * FROM applications WHERE id=?";
        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Application(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("job_id"),
                        rs.getTimestamp("applied_on"),
                        rs.getString("status")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
