package master.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // avoid creating a new session if none exists

        if (session != null) {
            session.invalidate(); // completely remove all session attributes
        }

        // Create a new session just to show the success message
        HttpSession newSession = req.getSession(true);
        newSession.setAttribute("succMsg", "Logged out successfully");

        resp.sendRedirect("login.jsp");
    }
}
