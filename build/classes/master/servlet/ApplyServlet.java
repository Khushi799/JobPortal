package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/apply")
public class ApplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String jobId = request.getParameter("jobId");
        HttpSession session = request.getSession(false);
        String seekerId = (session != null) ? (String) session.getAttribute("seekerUser") : null;

        if (seekerId == null) {
            response.sendRedirect("seekerLogin.jsp");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jobportal", "root", "password");

            // check if already applied
            PreparedStatement check = con.prepareStatement(
                "SELECT id FROM applications WHERE job_id = ? AND seeker_id = ?");
            check.setString(1, jobId);
            check.setString(2, seekerId);
            ResultSet rsCheck = check.executeQuery();
            if (rsCheck.next()) {
                rsCheck.close();
                check.close();
                con.close();
                response.sendRedirect("jobDetails.jsp?jobId=" + jobId + "&applied=already");
                return;
            }
            rsCheck.close();
            check.close();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO applications(job_id, seeker_id, applied_on) VALUES(?, ?, NOW())");
            ps.setString(1, jobId);
            ps.setString(2, seekerId);

            int i = ps.executeUpdate();
            if (i > 0) {
                response.sendRedirect("applySuccess.jsp");
            } else {
                response.sendRedirect("jobDetails.jsp?jobId=" + jobId + "&error=1");
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("jobDetails.jsp?jobId=" + jobId + "&error=db");
        }
    }
}
