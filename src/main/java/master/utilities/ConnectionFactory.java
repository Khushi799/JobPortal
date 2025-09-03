package master.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection cn = null;

    public static Connection getConn() {
        try {
            if (cn == null || cn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Load JDBC driver
                cn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/job_portal", // database name
                        "root",                                      // your MySQL username
                        "Khushi@2003"                                // your MySQL password
                );
                System.out.println("✅ Database Connected Successfully");
            }
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return cn;
    }
}
