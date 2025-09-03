package master.dao;

import java.sql.*;
import master.dto.User;
import master.utilities.ConnectionFactory;

public class UserDao {

    public boolean registerUser(master.dto.User user) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "INSERT INTO user(name, email, password, qualification, role) VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getQualification());
            ps.setString(5, user.getRole());
            return ps.executeUpdate() > 0;
        } catch(SQLException e) { e.printStackTrace(); return false; }
    }

    public User login(String email, String password) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "SELECT * FROM user WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("qualification"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }
        } catch(SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean updateUser(User user) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "UPDATE user SET name=?, qualification=?, email=?, password=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getQualification());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getId());
            return ps.executeUpdate() > 0;
        } catch(SQLException e) { e.printStackTrace(); return false; }
    }

    public User getUserById(int id) {
        try {
            Connection conn = ConnectionFactory.getConn();
            String sql = "SELECT * FROM user WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("qualification"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }
        } catch(SQLException e) { e.printStackTrace(); }
        return null;
    }
}
