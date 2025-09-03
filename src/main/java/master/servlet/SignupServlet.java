package master.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import master.dao.UserDao;
import master.dto.User;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String qualification = request.getParameter("qualification");
        String role = request.getParameter("role");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setQualification(qualification);
        user.setRole(role);

        UserDao dao = new UserDao();
        boolean status = dao.registerUser(user);

        if (status) {
            response.sendRedirect("login.jsp");
        } else {
            response.getWriter().println("Error in signup. Try again!");
        }
    }
}
