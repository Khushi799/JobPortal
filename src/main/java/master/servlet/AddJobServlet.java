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
        
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String location = request.getParameter("location");
        String salary = request.getParameter("salary");


        Job job = new Job();
        job.setTitle(title);
        job.setDescription(description);
        job.setCategory(category);
        job.setLocation(location);
        job.setSalary(salary);
        
        Connection cn = ConnectionFactory.getConn();

        JobDAO dao = new JobDAO(cn);
        dao.addJobs(job);

        response.sendRedirect("adminDashboard");
    }
}
