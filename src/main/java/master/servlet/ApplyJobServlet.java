package master.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import master.dao.ApplicationDAO;
import master.dto.Application;

@WebServlet("/applyJob")
public class ApplyJobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        int jobId = Integer.parseInt(request.getParameter("jobId"));
        int userId = (Integer) request.getSession().getAttribute("userId");

        Application app = new Application();
        app.setJobId(jobId);
        app.setUserId(userId);

        ApplicationDAO dao = new ApplicationDAO();
        boolean applied = dao.addApplication(app);

        if(applied){
            response.sendRedirect("viewApplications.jsp");
        } else {
            response.getWriter().println("Error applying for job");
        }
    }
}
