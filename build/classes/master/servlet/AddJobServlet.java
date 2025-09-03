package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.dao.JobDAO;
import com.dto.Job;

@WebServlet("/addJob")
public class AddJobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String location = request.getParameter("location");
        double salary = Double.parseDouble(request.getParameter("salary"));

        Job job = new Job();
        job.setTitle(title);
        job.setDescription(description);
        job.setCategory(category);
        job.setLocation(location);
        job.setSalary(salary);

        JobDAO dao = new JobDAO();
        dao.addJob(job);

        response.sendRedirect("adminDashboard");
    }
}
