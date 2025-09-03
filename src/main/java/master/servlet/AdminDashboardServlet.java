package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;
import com.dao.JobDAO;
import com.dao.ApplicationDAO;
import com.dto.Job;
import com.dto.Application;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JobDAO jobDAO = new JobDAO();
        ApplicationDAO appDAO = new ApplicationDAO();

        List<Job> jobs = jobDAO.getAllJobs();
        List<Application> applications = appDAO.getAllApplications();

        request.setAttribute("jobs", jobs);
        request.setAttribute("applications", applications);

        RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.jsp");
        rd.forward(request, response);
    }
}
