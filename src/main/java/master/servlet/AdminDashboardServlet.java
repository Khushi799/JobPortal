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

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int adminId = (Integer) session.getAttribute("adminId");
        //System.out.println("Admin ID in session: " + adminId);

        


        try (Connection cn = ConnectionFactory.getConn()) {
            // Fetch jobs posted by this admin
            
        	JobDAO jobDAO = new JobDAO(cn);
        	List<Job> jobs = jobDAO.getCompanyJobs(adminId); // instance call

        	ApplicationDAO appDAO = new ApplicationDAO();
        	List<Application> allApplications = new ArrayList<>();
        	for (Job job : jobs) {
        	    allApplications.addAll(appDAO.getApplicationsByJob(job.getId())); // now fetches job title dynamically
        	}

        	request.setAttribute("jobs", jobs);
        	request.setAttribute("applications", allApplications);
        	RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.jsp");
        	rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading admin dashboard!");
        }
    }
}


