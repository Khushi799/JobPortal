package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/companyLogin")
public class CompanyLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jobportal", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "SELECT id, name FROM company WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("companyUser", rs.getString("id")); // store company id
                session.setAttribute("companyName", rs.getString("name"));
                response.sendRedirect("companyDashboard.jsp");
            } else {
                response.sendRedirect("companyLogin.jsp?error=1");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("companyLogin.jsp?error=db");
        }
    }
}
