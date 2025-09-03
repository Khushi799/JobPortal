package master.dao;

import java.sql.*;
import master.dto.Admin;
import master.dto.Job;
import master.dto.User;
import master.utilities.ConnectionFactory;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
	

	    public boolean registerAdmin(User user) {
	        boolean status = false;
	        Connection con = null;
	        PreparedStatement ps = null;

	        try {
	            con = ConnectionFactory.getConn();
	            String sql = "INSERT INTO admin (company_name, email, password) VALUES (?, ?, ?)";
	            ps = con.prepareStatement(sql);

	            ps.setString(1, user.getName());
	            ps.setString(2, user.getEmail());
	            ps.setString(3, user.getPassword());
	            

	            int rows = ps.executeUpdate();
	            if (rows > 0) {
	                status = true;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try { if (ps != null) ps.close(); } catch (Exception e) {}
	            try { if (con != null) con.close(); } catch (Exception e) {}
	        }

	        return status;
	    }
	    

	
	

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
                    rs.getString("password")
                    
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

    
}
