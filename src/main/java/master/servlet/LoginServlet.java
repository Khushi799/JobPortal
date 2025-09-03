package master.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import master.dao.UserDao;
import master.dao.AdminDao;
import master.dto.User;
import master.dto.Admin;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        // Try Admin login first
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.login(email, password);

        if (admin != null) {
            session.setAttribute("admin", admin);
            session.setAttribute("adminId", admin.getId());
            response.sendRedirect("adminDashboard"); // ✅ servlet URL, not JSP
            return;
        }

        // Otherwise, try User login
        UserDao userDao = new UserDao();
        User user = userDao.login(email, password);

        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            response.sendRedirect("userDashboard.jsp"); // if you have a servlet for user dashboard
        }

}
}
