package master.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;
import master.dao.JobDAO;
import master.dto.Job;
import master.utilities.ConnectionFactory;

@WebServlet("/searchJobs")
public class SearchJobServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String location = request.getParameter("location");
        String category = request.getParameter("category");
        Connection cn = ConnectionFactory.getConn();

        JobDAO jobDAO = new JobDAO(cn);
        List<Job> jobs = jobDAO.getJobsByCategoryOrLocation(category,location);

        request.setAttribute("jobs", jobs);
        RequestDispatcher rd = request.getRequestDispatcher("userDashboard.jsp");
        rd.forward(request, response);
    }
}

