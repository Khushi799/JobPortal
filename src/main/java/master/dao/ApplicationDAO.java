package master.dao;

import master.dto.Application;
import master.utilities.ConnectionFactory; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    // 1. Add a new application
    public boolean addApplication(Application app) {
        String sql = "INSERT INTO application (user_id, job_id, email, qualification, experience, cover_letter, applied_on, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, app.getUserId());
            ps.setInt(2, app.getJobId());
            ps.setString(3, app.getEmail());
            ps.setString(4, app.getQualification());
            ps.setInt(5, app.getExperience());
            ps.setString(6, app.getCoverLetter());
            ps.setTimestamp(7, app.getAppliedOn() != null ? app.getAppliedOn() : new Timestamp(System.currentTimeMillis()));
            ps.setString(8, app.getStatus() != null ? app.getStatus() : "Pending");

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Update application status
    public boolean updateApplicationStatus(int applicationId, String status) {
        String sql = "UPDATE application SET status=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, applicationId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Get application by ID
    public Application getApplicationById(int id) {
        String sql = "SELECT a.id, a.user_id, a.job_id, a.email, a.qualification, a.experience, a.cover_letter, a.applied_on, a.status, j.title AS job_title " +
                     "FROM application a JOIN job j ON a.job_id=j.id WHERE a.id=?";
        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Application app = new Application();
                    app.setId(rs.getInt("id"));
                    app.setUserId(rs.getInt("user_id"));
                    app.setJobId(rs.getInt("job_id"));
                    app.setEmail(rs.getString("email"));
                    app.setQualification(rs.getString("qualification"));
                    app.setExperience(rs.getInt("experience"));
                    app.setCoverLetter(rs.getString("cover_letter"));
                    app.setAppliedOn(rs.getTimestamp("applied_on"));
                    app.setStatus(rs.getString("status"));
                    app.setJobTitle(rs.getString("job_title"));
                    return app;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. Get applications by user
    public List<Application> getApplicationsByUser(int userId) {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT a.id, a.user_id, a.job_id, a.email, a.qualification, a.experience, a.cover_letter, a.status, j.title AS job_title " +
                     "FROM application a JOIN job j ON a.job_id = j.id WHERE a.user_id = ?";
        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Application app = new Application();
                    app.setId(rs.getInt("id"));
                    app.setUserId(rs.getInt("user_id"));
                    app.setJobId(rs.getInt("job_id"));
                    app.setEmail(rs.getString("email"));
                    app.setQualification(rs.getString("qualification"));
                    app.setExperience(rs.getInt("experience"));
                    app.setCoverLetter(rs.getString("cover_letter"));
                    app.setStatus(rs.getString("status"));
                    app.setJobTitle(rs.getString("job_title"));
                    list.add(app);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 5. Get applications by job
    public List<Application> getApplicationsByJob(int jobId) {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT a.id, a.user_id, a.job_id, a.email, a.qualification, a.experience, a.cover_letter, a.status, j.title AS job_title " +
                     "FROM application a JOIN job j ON a.job_id = j.id WHERE a.job_id = ?";
        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jobId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Application app = new Application();
                    app.setId(rs.getInt("id"));
                    app.setUserId(rs.getInt("user_id"));
                    app.setJobId(rs.getInt("job_id"));
                    app.setEmail(rs.getString("email"));
                    app.setQualification(rs.getString("qualification"));
                    app.setExperience(rs.getInt("experience"));
                    app.setCoverLetter(rs.getString("cover_letter"));
                    app.setStatus(rs.getString("status"));
                    app.setJobTitle(rs.getString("job_title"));
                    list.add(app);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 6. ✅ Get all applications (for Admin Dashboard)
    public List<Application> getAllApplications() {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT a.id, a.email, a.qualification, a.experience, j.title AS job_title " +
                     "FROM application a JOIN job j ON a.job_id = j.id";
        try (Connection conn = ConnectionFactory.getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Application app = new Application();
                app.setId(rs.getInt("id"));
                app.setEmail(rs.getString("email"));
                app.setQualification(rs.getString("qualification"));
                app.setExperience(rs.getInt("experience"));
                app.setJobTitle(rs.getString("job_title"));
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
