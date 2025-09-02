package master.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import master.utilities.ConnectionFactory;
import master.dao.JobDAO;

@WebServlet("/delete")
public class DeleteJobServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            String idParam = req.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                session.setAttribute("errMsg", "Invalid Job ID");
                resp.sendRedirect("view_job.jsp");
                return;
            }

            int id = Integer.parseInt(idParam);

            JobDAO dao = new JobDAO(ConnectionFactory.getConn());
            boolean isDeleted = dao.deleteJob(id);

            if (isDeleted) {
                session.setAttribute("succMsg", "Job Deleted Successfully.");
            } else {
                session.setAttribute("errMsg", "Something went wrong on server!");
            }

            resp.sendRedirect("view_job.jsp");

        } catch (NumberFormatException e) {
            session.setAttribute("errMsg", "Job ID must be a number!");
            resp.sendRedirect("view_job.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errMsg", "Server error: " + e.getMessage());
            resp.sendRedirect("view_job.jsp");
        }
    }
}
