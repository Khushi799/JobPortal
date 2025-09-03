package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/status")
public class StatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String applicationId = request.getParameter("applicationId");
        String newStatus = request.getParameter("status"); 

        if (applicationId == null || newStatus == null) {
            response.sendRedirect("applications.jsp?error=invalid");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jobportal", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "UPDATE applications SET status = ? WHERE id = ?");
            ps.setString(1, newStatus);
            ps.setString(2, applicationId);

            int i = ps.executeUpdate();
            if (i > 0) {
                response.sendRedirect("applications.jsp?updated=1");
            } else {
                response.sendRedirect("applications.jsp?error=1");
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("applications.jsp?error=db");
        }
    }
}
