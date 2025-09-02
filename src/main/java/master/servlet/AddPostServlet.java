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
import master.dto.User;

@WebServlet("/add_job")
public class AddPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            // Get logged-in user
            User user = (User) session.getAttribute("userobj");
            if (user == null) {
                session.setAttribute("errMsg", "Please login first to post a job!");
                resp.sendRedirect("login.jsp");
                return;
            }

            // Get form parameters
            String title = req.getParameter("title");
            String location = req.getParameter("location");
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            String description = req.getParameter("desc");

            // Basic validation
            if (title == null || title.isEmpty() || location == null || location.isEmpty() ||
                category == null || category.isEmpty() || status == null || status.isEmpty() ||
                description == null || description.isEmpty()) {
                session.setAttribute("errMsg", "All fields are required!");
                resp.sendRedirect("add_job.jsp");
                return;
            }

            // Create Jobs object
            Jobs job = new Jobs();
            job.setTitle(title);
            job.setDescription(description);
            job.setLocation(location);
            job.setStatus(status);
            job.setCategory(category);
            job.setPostedBy(user.getId()); // Set posted_by according to DB

            // Save job using DAO
            JobDAO dao = new JobDAO(ConnectionFactory.getConn());
            boolean success = dao.addJobs(job);

            if (success) {
                session.setAttribute("succMsg", "Job Posted Successfully!");
            } else {
                session.setAttribute("errMsg", "Something went wrong on the server!");
            }

            resp.sendRedirect("add_job.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errMsg", "Internal error occurred: " + e.getMessage());
            resp.sendRedirect("add_job.jsp");
        }
    }
}
