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
import master.dto.Application;
import master.utilities.ConnectionFactory;

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

        try (Connection cn = ConnectionFactory.getConn()) {
            // ✅ Fetch jobs posted by this admin
            JobDAO jobDAO = new JobDAO(cn);
            List<Job> jobs = jobDAO.getCompanyJobs(adminId);

            // ✅ Fetch all applications (email, qualification, experience, jobTitle only)
            ApplicationDAO appDAO = new ApplicationDAO();
            List<Application> applications = appDAO.getAllApplications();

            // ✅ Set attributes for JSP
            request.setAttribute("jobs", jobs);
            request.setAttribute("applications", applications);

            RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading admin dashboard!");
        }
    }
}
