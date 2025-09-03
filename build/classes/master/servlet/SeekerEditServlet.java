package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/editSeeker")
public class SeekerEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String seekerId = (session != null) ? (String) session.getAttribute("seekerUser") : null;
        if (seekerId == null) {
            response.sendRedirect("seekerLogin.jsp");
            return;
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String resume = request.getParameter("resume");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jobportal", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "UPDATE seeker SET name = ?, email = ?, resume = ? WHERE id = ?");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, resume);
            ps.setString(4, seekerId);

            int i = ps.executeUpdate();
            if (i > 0) {
                response.sendRedirect("seekerProfile.jsp?updated=1");
            } else {
                response.sendRedirect("seekerProfile.jsp?error=1");
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("seekerProfile.jsp?error=db");
        }
    }
}
