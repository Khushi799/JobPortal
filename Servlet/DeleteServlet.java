package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type"); // e.g., "vacancy", "application", "company", "seeker"
        String id = request.getParameter("id");

        if (type == null || id == null) {
            response.sendRedirect("adminDashboard.jsp?error=invalid");
            return;
        }

        String table;
        switch (type) {
            case "vacancy": table = "vacancy"; break;
            case "application": table = "applications"; break;
            case "company": table = "company"; break;
            case "seeker": table = "seeker"; break;
            default:
                response.sendRedirect("adminDashboard.jsp?error=invalid");
                return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jobportal", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM " + table + " WHERE id = ?");
            ps.setString(1, id);

            int i = ps.executeUpdate();
            if (i > 0) {
                response.sendRedirect("adminDashboard.jsp?deleted=1");
            } else {
                response.sendRedirect("adminDashboard.jsp?error=1");
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminDashboard.jsp?error=db");
        }
    }
}
