package master.dao;

import java.sql.*;
import master.dto.Admin;
import master.dto.Job;
import master.utilities.ConnectionFactory;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    public Admin login(String email, String password) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "SELECT * FROM admin WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Admin(
                    rs.getInt("id"),
                    rs.getString("company_name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("description")
                );
            }
        } catch(SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean updateAdmin(Admin admin) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "UPDATE admin SET company_name=?, description=?, email=?, password=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, admin.getCompanyName());
            ps.setString(2, admin.getDescription());
            ps.setString(3, admin.getEmail());
            ps.setString(4, admin.getPassword());
            ps.setInt(5, admin.getId());
            return ps.executeUpdate() > 0;
        } catch(SQLException e) { e.printStackTrace(); return false; }
    }

    public List<master.dto.Job> getCompanyJobs(int adminId) {
        List<master.dto.Job> jobs = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "SELECT * FROM jobs WHERE company_id=? ORDER BY posted_on DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, adminId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Job job = new Job(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getInt("company_id"),
                    rs.getString("location"),
                    rs.getString("salary"),
                    rs.getTimestamp("posted_on")
                );
                jobs.add(job);
            }
        } catch(SQLException e) { e.printStackTrace(); }
        return jobs;
    }
}
