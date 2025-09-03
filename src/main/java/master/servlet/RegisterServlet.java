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

@WebServlet("/add_user")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            String name = req.getParameter("name");
            String qualification = req.getParameter("qualification");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            // Basic validation
            if (name == null || name.isEmpty() || email == null || email.isEmpty() 
                    || password == null || password.isEmpty() || qualification == null || qualification.isEmpty()) {
                session.setAttribute("errMsg", "All fields are required!");
                resp.sendRedirect("signup.jsp");
                return;
            }

            UserDAO dao = new UserDAO(ConnectionFactory.getConn());

            // Check if email already exists
            if (dao.isEmailExist(email)) {
                session.setAttribute("errMsg", "Email already registered!");
                resp.sendRedirect("signup.jsp");
                return;
            }

            User user = new User(name, email, password, qualification, "User");
            boolean added = dao.addUser(user);

            if (added) {
                session.setAttribute("succMsg", "Registered Successfully!");
                resp.sendRedirect("login.jsp");
                return;
            } else {
                session.setAttribute("errMsg", "Something went wrong on server!");
                resp.sendRedirect("signup.jsp");
                return;
            }

            

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errMsg", "Server Error: " + e.getMessage());
            resp.sendRedirect("signup.jsp");
        }
    }
}
