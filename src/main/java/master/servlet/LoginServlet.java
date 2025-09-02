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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            String em = req.getParameter("email");
            String ps = req.getParameter("password");

            if (em == null || ps == null || em.isEmpty() || ps.isEmpty()) {
                session.setAttribute("errMsg", "Email and Password cannot be empty");
                resp.sendRedirect("login.jsp");
                return;
            }

            // Admin login check
            if ("admin@gmail.com".equals(em) && "admin@123".equals(ps)) {
                User admin = new User();
                admin.setEmail(em);
                admin.setRole("admin");
                session.setAttribute("userobj", admin);
                resp.sendRedirect("admin.jsp");
                return;
            }

            // Regular user login
            UserDAO dao = new UserDAO(ConnectionFactory.getConn());
            User user = dao.login(em, ps);

            if (user != null) {
                session.setAttribute("userobj", user);
                resp.sendRedirect("home.jsp");
            } else {
                session.setAttribute("errMsg", "Invalid Email or Password");
                resp.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errMsg", "Server Error: " + e.getMessage());
            resp.sendRedirect("login.jsp");
        }
    }
}
