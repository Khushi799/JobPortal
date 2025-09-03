package master.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import master.dao.UserDao;
import master.dao.AdminDao;
import master.dto.User;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        boolean status = false;

        if ("admin".equalsIgnoreCase(role)) {
            // Admin has only name, email, password
            String adminName = request.getParameter("name");
            user.setName(adminName);

            AdminDao adminDao = new AdminDao();
            status = adminDao.registerAdmin(user);

        } else if ("user".equalsIgnoreCase(role) || "seeker".equalsIgnoreCase(role)) {
            // User/Seeker has name, email, password, qualification
            String name = request.getParameter("name");
            String qualification = request.getParameter("qualification");
            user.setName(name);
            user.setQualification(qualification);

            UserDao userDao = new UserDao();
            status = userDao.registerUser(user);
        }

        if (status) {
            response.sendRedirect("login.jsp");
        } else {
            response.getWriter().println("Error in signup. Try again!");
        }
    }
}
