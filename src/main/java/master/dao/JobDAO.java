package master.dao;

import master.dto.Job;
import master.utilities.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    private final Connection conn;

    public JobDAO(Connection connection) {
        this.conn = connection;
    }

    // Add a new job
    public boolean addJobs(Job job) {
        String sql = "INSERT INTO job (title, description, category, status, location, company_id, salary, posted_on) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, job.getTitle());
            ps.setString(2, job.getDescription());
            ps.setString(3, job.getCategory());
            ps.setString(4, job.getStatus());
            ps.setString(5, job.getLocation());
            ps.setInt(6, job.getCompanyId()); // ✅ matches company_id
            ps.setString(7, job.getSalary());
            ps.setTimestamp(8, job.getPostedOn() != null ? job.getPostedOn() : new Timestamp(System.currentTimeMillis())); // ✅ matches posted_on

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Map ResultSet to Job object
 // Map ResultSet to Job object
    private Job map(ResultSet rs) throws SQLException {
        Job j = new Job();
        j.setId(rs.getInt("id"));
        j.setTitle(rs.getString("title"));
        j.setDescription(rs.getString("description"));
        j.setCategory(rs.getString("category"));
        j.setStatus(rs.getString("status"));
        j.setLocation(rs.getString("location"));
        j.setCompanyId(rs.getInt("company_id"));   // ✅ fixed
        j.setSalary(rs.getString("salary"));
        j.setPostedOn(rs.getTimestamp("posted_on")); // ✅ fixed
        return j;
    }



    // Get all jobs
    public List<Job> getAllJobs() {
        List<Job> list = new ArrayList<>();
        String sql = "SELECT * FROM job ORDER BY id DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get job by ID
    public Job getJobById(int id) {
        String sql = "SELECT * FROM job WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update job
    public boolean updateJob(Job job) {
        String sql = "UPDATE job SET title=?, description=?, category=?, status=?, location=?, salary=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, job.getTitle());
            ps.setString(2, job.getDescription());
            ps.setString(3, job.getCategory());
            ps.setString(4, job.getStatus());
            ps.setString(5, job.getLocation());
            ps.setString(6, job.getSalary());
            ps.setInt(7, job.getId());

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Delete job
    public boolean deleteJob(int id) {
        String sql = "DELETE FROM job WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get jobs by category OR location
    public List<Job> getJobsByCategoryOrLocation(String category, String location) {
        List<Job> list = new ArrayList<>();
        String sql = "SELECT * FROM job WHERE category=? OR location=? ORDER BY id DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            ps.setString(2, location);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get jobs by category AND location
    public List<Job> getJobsByCategoryAndLocation(String category, String location) {
        List<Job> list = new ArrayList<>();
        String sql = "SELECT * FROM job WHERE category=? AND location=? ORDER BY id DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            ps.setString(2, location);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Job> getCompanyJobs(int adminId) {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM job WHERE company_id = ? ORDER BY posted_on DESC";

        try (PreparedStatement ps = this.conn.prepareStatement(sql)) {
            ps.setInt(1, adminId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Job job = map(rs);
                    jobs.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

}
