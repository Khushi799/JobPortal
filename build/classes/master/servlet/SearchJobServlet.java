package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;
import com.dao.JobDAO;
import com.dto.JobDTO;

@WebServlet("/searchJobs")
public class SearchJobServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");

        JobDAO jobDAO = new JobDAO();
        List<JobDTO> jobs = jobDAO.searchJobs(keyword, category);

        request.setAttribute("jobs", jobs);
        RequestDispatcher rd = request.getRequestDispatcher("userDashboard.jsp");
        rd.forward(request, response);
    }
}
