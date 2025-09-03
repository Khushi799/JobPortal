package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.dao.JobDAO;

@WebServlet("/deleteJob")
public class DeleteJobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int jobId = Integer.parseInt(request.getParameter("jobId"));

        JobDAO dao = new JobDAO();
        dao.deleteJob(jobId);

        response.sendRedirect("adminDashboard");
    }
}
