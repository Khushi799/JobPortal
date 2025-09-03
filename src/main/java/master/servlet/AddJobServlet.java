package master.servlet;

import java.io.*;
import java.sql.Connection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import master.dao.JobDAO;
import master.dto.Job;
import master.utilities.ConnectionFactory;

@WebServlet("/addJob")
public class AddJobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get session and adminId
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int companyId = (Integer) session.getAttribute("adminId"); // ✅ get admin id from session

        // Collect job data from form
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String location = request.getParameter("location");
        String salary = request.getParameter("salary");

        // Build job object
        Job job = new Job();
        job.setTitle(title);
        job.setDescription(description);
        job.setCategory(category);
        job.setLocation(location);
        job.setSalary(salary);
        job.setCompanyId(companyId);   // ✅ admin.id becomes job.company_id
        job.setStatus("Open");

        try (Connection cn = ConnectionFactory.getConn()) {
            JobDAO dao = new JobDAO(cn);
            dao.addJobs(job);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("adminDashboard");
    }
}
