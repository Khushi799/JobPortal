package master.dao;

import master.dto.Jobs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {
    private final Connection conn;

    public JobDAO(Connection connection) {
        this.conn = connection;
    }

    public boolean addJobs(Jobs job) {
        boolean result = false;
        String sql = "INSERT INTO jobs (title, description, category, status, location, posted_by) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, job.getTitle());
            ps.setString(2, job.getDescription());
            ps.setString(3, job.getCategory());
            ps.setString(4, job.getStatus());
            ps.setString(5, job.getLocation());
            ps.setInt(6, job.getPostedBy()); // set posted_by

            int rows = ps.executeUpdate();
            if (rows > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private Jobs map(ResultSet rs) throws SQLException {
        Jobs j = new Jobs();
        j.setId(rs.getInt("id"));
        j.setTitle(rs.getString("title"));
        j.setDescription(rs.getString("description"));
        j.setCategory(rs.getString("category"));
        j.setStatus(rs.getString("status"));
        j.setLocation(rs.getString("location"));
        j.setPdate(rs.getTimestamp("pdate")); // uses the Timestamp overload
        return j;
    }

    public List<Jobs> getAllJobs() {
        List<Jobs> list = new ArrayList<>();
        String sql = "SELECT * FROM jobs ORDER BY id DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Jobs getJobById(int id) {
        String sql = "SELECT * FROM jobs WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean updateJob(Jobs j) {
        String sql = "UPDATE jobs SET title=?, description=?, category=?, status=?, location=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, j.getTitle());
            ps.setString(2, j.getDescription());
            ps.setString(3, j.getCategory());
            ps.setString(4, j.getStatus());
            ps.setString(5, j.getLocation());
            ps.setInt(6, j.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteJob(int id) {
        String sql = "DELETE FROM jobs WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Jobs> getJobsByCategoryOrLocation(String category, String location) {
        List<Jobs> list = new ArrayList<>();
        String sql = "SELECT * FROM jobs WHERE category=? OR location=? ORDER BY id DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            ps.setString(2, location);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Jobs> getJobsByCategoryAndLocation(String category, String location) {
        List<Jobs> list = new ArrayList<>();
        String sql = "SELECT * FROM jobs WHERE category=? AND location=? ORDER BY id DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            ps.setString(2, location);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
