package master.servlet;

import java.io.*;
import java.sql.Connection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;
import master.dao.JobDAO;
import master.dao.ApplicationDAO;
import master.dto.Job;
import master.utilities.ConnectionFactory;
import master.dto.Application;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection cn = ConnectionFactory.getConn();

        JobDAO jobDAO = new JobDAO(cn);
        ApplicationDAO appDAO = new ApplicationDAO();

        List<Job> jobs = jobDAO.getAllJobs();

        List<Application> allApplications = new ArrayList<>();
        for (Job job : jobs) {
            List<Application> apps = appDAO.getApplicationsByJob(job.getId());
            allApplications.addAll(apps);
        }

        request.setAttribute("jobs", jobs);
        request.setAttribute("applications", allApplications);

        RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.jsp");
        rd.forward(request, response);
    }
}

