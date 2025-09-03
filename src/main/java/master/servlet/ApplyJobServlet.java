package master.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import master.dao.ApplicationDAO;
import master.dto.Application;
import java.sql.Timestamp;

@WebServlet("/applyJob")
public class ApplyJobServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        try {
            int jobId = Integer.parseInt(request.getParameter("jobId"));
            int userId = (Integer) request.getSession().getAttribute("userId");

            String email = request.getParameter("email");
            String qualification = request.getParameter("qualification");
            int experience = Integer.parseInt(request.getParameter("experience"));
            String coverLetter = request.getParameter("coverLetter");

            Application app = new Application();
            app.setJobId(jobId);
            app.setUserId(userId);
            app.setEmail(email);
            app.setQualification(qualification);
            app.setExperience(experience);
            app.setCoverLetter(coverLetter);
            app.setAppliedOn(new Timestamp(System.currentTimeMillis()));
            app.setStatus("Pending");

            ApplicationDAO dao = new ApplicationDAO();
            boolean applied = dao.addApplication(app);

            if (applied) {
                response.sendRedirect("viewApplications.jsp?status=success");
            } else {
                response.sendRedirect("apply.jsp?jobId=" + jobId + "&error=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("apply.jsp?jobId=" + request.getParameter("jobId") + "&error=exception");
        }
    }
}
