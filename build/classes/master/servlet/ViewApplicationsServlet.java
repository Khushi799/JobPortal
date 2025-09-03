package backend;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;
import com.dao.ApplicationDAO;
import com.dto.ApplicationDTO;

@WebServlet("/viewApplications")
public class ViewApplicationsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        int userId = (Integer) request.getSession().getAttribute("userId");

        ApplicationDAO dao = new ApplicationDAO();
        List<ApplicationDTO> applications = dao.getApplicationsByUser(userId);

        request.setAttribute("applications", applications);
        RequestDispatcher rd = request.getRequestDispatcher("viewApplications.jsp");
        rd.forward(request, response);
    }
}
