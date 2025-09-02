package master.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import master.utilities.ConnectionFactory;
import master.dao.UserDAO;
import master.dto.User;

@WebServlet("/update_profile")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            // Get the logged-in user from session
            User sessionUser = (User) session.getAttribute("userobj");
            if (sessionUser == null) {
                session.setAttribute("errMsg", "Please login first!");
                resp.sendRedirect("login.jsp");
                return;
            }

            // Read updated parameters from the form
            String name = req.getParameter("name");
            String qualification = req.getParameter("qualification");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            // Basic validation
            if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
                session.setAttribute("errMsg", "All fields are required!");
                resp.sendRedirect("home.jsp");
                return;
            }

            // Update the user object
            sessionUser.setName(name);
            sessionUser.setQualification(qualification);
            sessionUser.setEmail(email);
            sessionUser.setPassword(password);

            // Update in database
            UserDAO dao = new UserDAO(ConnectionFactory.getConn());
            boolean updated = dao.updateUser(sessionUser);

            if (updated) {
                session.setAttribute("succMsg", "Profile Updated Successfully");
            } else {
                session.setAttribute("errMsg", "Something went wrong on server!");
            }

            resp.sendRedirect("home.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errMsg", "Server Error: " + e.getMessage());
            resp.sendRedirect("home.jsp");
        }
    }
}
