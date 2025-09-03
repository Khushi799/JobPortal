package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.dao.ApplicationDAO;
import com.dto.ApplicationDTO;

@WebServlet("/applyJob")
public class ApplyJobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        int jobId = Integer.parseInt(request.getParameter("jobId"));
        int userId = (Integer) request.getSession().getAttribute("userId");

        ApplicationDTO app = new ApplicationDTO();
        app.setJobId(jobId);
        app.setUserId(userId);

        ApplicationDAO dao = new ApplicationDAO();
        boolean applied = dao.applyJob(app);

        if(applied){
            response.sendRedirect("viewApplications.jsp");
        } else {
            response.getWriter().println("Error applying for job");
        }
    }
}
