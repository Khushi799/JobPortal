package master.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;
import master.dao.ApplicationDAO;
import master.dto.Application;

@WebServlet("/viewApplications")
public class ViewApplicationsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        int userId = (Integer) request.getSession().getAttribute("userId");

        ApplicationDAO dao = new ApplicationDAO();
        List<Application> applications = dao.getApplicationsByUser(userId);

        request.setAttribute("applications", applications);
        RequestDispatcher rd = request.getRequestDispatcher("viewApplications.jsp");
        rd.forward(request, response);
    }
}
