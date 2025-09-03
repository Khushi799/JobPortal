package master.servlet;

import java.io.*;
import java.sql.Connection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import master.dao.JobDAO;

import master.utilities.ConnectionFactory;

@WebServlet("/deleteJob")
public class DeleteJobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        Connection cn = ConnectionFactory.getConn();

        JobDAO dao = new JobDAO(cn);
        dao.deleteJob(jobId);

        response.sendRedirect("adminDashboard");
    }
}
