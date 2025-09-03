package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/vacancyDetails")
public class VacancyDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String vacancyId = request.getParameter("id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jobportal", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM vacancy WHERE id=?");
            ps.setString(1, vacancyId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("vacancy", rs);
                RequestDispatcher rd = request.getRequestDispatcher("vacancyDetails.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect("vacancies.jsp?error=notfound");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("vacancies.jsp?error=db");
        }
    }
}
