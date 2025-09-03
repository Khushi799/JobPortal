package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/companyRegister")
public class CompanyRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jobportal", "root", "password");

            PreparedStatement check = con.prepareStatement(
                "SELECT id FROM company WHERE email = ?");
            check.setString(1, email);
            ResultSet rsCheck = check.executeQuery();
            if (rsCheck.next()) {
                // email already registered
                rsCheck.close();
                check.close();
                con.close();
                response.sendRedirect("companyRegistration.jsp?error=exists");
                return;
            }
            rsCheck.close();
            check.close();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO company(name, email, password, phone) VALUES(?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, phone);

            int i = ps.executeUpdate();
            if (i > 0) {
                response.sendRedirect("companyLogin.jsp?registered=1");
            } else {
                response.sendRedirect("companyRegistration.jsp?error=1");
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("companyRegistration.jsp?error=db");
        }
    }
}
