package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/editCompany")
public class EditCompanyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String companyId = (session != null) ? (String) session.getAttribute("companyUser") : null;
        if (companyId == null) {
            response.sendRedirect("companyLogin.jsp");
            return;
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String about = request.getParameter("about");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jobportal", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "UPDATE company SET name = ?, email = ?, phone = ?, about = ? WHERE id = ?");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, about);
            ps.setString(5, companyId);

            int i = ps.executeUpdate();
            if (i > 0) {
                response.sendRedirect("companyProfile.jsp?updated=1");
            } else {
                response.sendRedirect("companyProfile.jsp?error=1");
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("companyProfile.jsp?error=db");
        }
    }
}
