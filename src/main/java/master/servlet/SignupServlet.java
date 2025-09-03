package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.dao.UserDAO;
import com.dto.UserDTO;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String qualification = request.getParameter("qualification");
        String role = request.getParameter("role");

        UserDTO user = new UserDTO();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setQualification(qualification);
        user.setRole(role);

        UserDAO dao = new UserDAO();
        boolean status = dao.addUser(user);

        if (status) {
            response.sendRedirect("login.jsp");
        } else {
            response.getWriter().println("Error in signup. Try again!");
        }
    }
}
