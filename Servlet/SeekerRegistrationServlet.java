package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/seekerRegister")
public class SeekerRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String resumeLink = request.getParameter("resume"); // optional

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jobportal", "root", "password");

            PreparedStatement check = con.prepareStatement(
                "SELECT id FROM seeker WHERE email = ?");
            check.setString(1, email);
            ResultSet rsCheck = check.executeQuery();
            if (rsCheck.next()) {
                rsCheck.close();
                check.close();
                con.close();
                response.sendRedirect("seekerRegistration.jsp?error=exists");
                return;
            }
            rsCheck.close();
            check.close();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO seeker(name, email, password, resume) VALUES(?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, resumeLink);

            int i = ps.executeUpdate();
            if (i > 0) {
                response.sendRedirect("seekerLogin.jsp?registered=1");
            } else {
                response.sendRedirect("seekerRegistration.jsp?error=1");
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("seekerRegistration.jsp?error=db");
        }
    }
}
