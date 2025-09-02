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
import master.dto.Jobs;

@WebServlet("/update")
public class UpdateJobServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String location = req.getParameter("location");
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            String desc = req.getParameter("desc");

            // Basic validation
            if (title == null || title.isEmpty() || location == null || location.isEmpty() || category == null || category.isEmpty()) {
                session.setAttribute("errMsg", "Title, Location, and Category are required!");
                resp.sendRedirect("view_job.jsp");
                return;
            }

            Jobs job = new Jobs();
            job.setId(id);
            job.setTitle(title);
            job.setDescription(desc);
            job.setLocation(location);
            job.setStatus(status);
            job.setCategory(category);

            JobDAO dao = new JobDAO(ConnectionFactory.getConn());
            boolean updated = dao.updateJob(job);

            if (updated) {
                session.setAttribute("succMsg", "Job Updated Successfully.");
            } else {
                session.setAttribute("errMsg", "Something went wrong on server!");
            }

            resp.sendRedirect("view_job.jsp");

        } catch (NumberFormatException e) {
            session.setAttribute("errMsg", "Invalid Job ID!");
            resp.sendRedirect("view_job.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errMsg", "Server Error: " + e.getMessage());
            resp.sendRedirect("view_job.jsp");
        }
    }
}
