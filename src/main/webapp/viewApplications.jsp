<%@ page import="java.util.*, master.dao.ApplicationDAO, master.dto.Application" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Applications</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef5fb;
            margin: 0;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #2b4c7e;
            margin-bottom: 20px;
        }
        table {
            width: 90%;
            margin: auto;
            border-collapse: collapse;
            background: white;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            padding: 12px 15px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #6fa8dc;
            color: white;
        }
        tr:hover {
            background-color: #f1f9ff;
        }
    </style>
</head>
<body>
    <h2>Applications</h2>

    <%
        // Example: if admin is viewing by job
        String jobIdParam = request.getParameter("jobId");
        List<Application> applications = new ArrayList<>();
        ApplicationDAO dao = new ApplicationDAO();

        if (jobIdParam != null) {
            int jobId = Integer.parseInt(jobIdParam);
            applications = dao.getApplicationsByJob(jobId);
        } else {
            // if user is viewing their own applications
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId != null) {
                applications = dao.getApplicationsByUser(userId);
            }
        }
    %>

    <table>
        <tr>
            <th>ID</th>
            <th>User Name</th>
            <th>Job Title</th>
            <th>Email</th>
            <th>Qualification</th>
            <th>Experience (years)</th>
            <th>Cover Letter</th>
            <th>Status</th>
            <th>Applied On</th>
        </tr>
        <%
            for (Application app : applications) {
        %>
        <tr>
            <td><%= app.getId() %></td>
            <td><%= app.getUserName() != null ? app.getUserName() : "-" %></td>
            <td><%= app.getJobTitle() != null ? app.getJobTitle() : "-" %></td>
            <td><%= app.getEmail() %></td>
            <td><%= app.getQualification() %></td>
            <td><%= app.getExperience() %></td>
            <td><%= app.getCoverLetter() %></td>
            <td><%= app.getStatus() %></td>
            <td><%= app.getAppliedOn() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
