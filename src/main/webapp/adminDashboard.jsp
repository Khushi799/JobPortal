<%@ page import="java.util.List" %>
<%@ page import="master.dto.Job" %>
<%@ page import="master.dto.Application" %>

<%
    // ✅ Prevent access if admin is not logged in
    if (session == null || session.getAttribute("adminId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Job> jobs = (List<Job>) request.getAttribute("jobs");
    List<Application> applications = (List<Application>) request.getAttribute("applications");
%>

<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e9f4fb; /* lighter blue */
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h2 {
            color: #1e3d59;
            margin-top: 20px;
        }

        .container {
            width: 85%;
            margin: 20px auto;
            background: rgba(255, 255, 255, 0.95);
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0px 4px 12px rgba(0,0,0,0.1);
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
        }

        .top-bar a, 
        .top-bar form input[type="submit"] {
            background: #5dade2;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 6px;
            text-decoration: none;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .top-bar a:hover, 
        .top-bar form input[type="submit"]:hover {
            background: #3498db;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 15px;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0px 3px 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: center; /* ✅ Center-align table content */
        }

        th {
            background-color: #aed6f1; /* light blue shade */
            color: #1e3d59;
        }

        tr:hover {
            background-color: #f2f9fc;
        }

        .delete-btn {
            background: #e74c3c;
            color: white;
            padding: 6px 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .delete-btn:hover {
            background: #c0392b;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="top-bar">
            <h2>Admin Dashboard</h2>
            <form action="logout" method="get">
                <input type="submit" value="Logout"/>
            </form>
        </div>

        <div class="top-bar">
            <a href="addJob.jsp">+ Add New Job</a>
        </div>

        <h3>All Jobs</h3>
        <table>
            <tr>
                <th>ID</th><th>Title</th><th>Category</th><th>Location</th><th>Salary</th><th>Action</th>
            </tr>
            <%
                if (jobs != null && !jobs.isEmpty()) {
                    for (Job job : jobs) {
            %>
            <tr>
                <td><%= job.getId() %></td>
                <td><%= job.getTitle() %></td>
                <td><%= job.getCategory() %></td>
                <td><%= job.getLocation() %></td>
                <td><%= job.getSalary() %></td>
                <td>
                    <form action="deleteJob" method="post" style="display:inline;">
                        <input type="hidden" name="jobId" value="<%= job.getId() %>"/>
                        <input type="submit" value="Delete" class="delete-btn"/>
                    </form>
                </td>
            </tr>
            <%      }
                } else {
            %>
            <tr><td colspan="6">No jobs found.</td></tr>
            <%  } %>
        </table>

       <h3>Applications</h3>
<table>
    <tr>
        <th>Email</th>
        <th>Qualification</th>
        <th>Experience</th>
        <th>Job Title</th>
    </tr>
    <%
        if (applications != null && !applications.isEmpty()) {
            for (Application app : applications) {
    %>
    <tr>
        <td><%= app.getEmail() %></td>
        <td><%= app.getQualification() %></td>
        <td><%= app.getExperience() %></td>
        <td><%= app.getJobTitle() %></td>
    </tr>
    <%      }
        } else {
    %>
    <tr><td colspan="4">No applications found.</td></tr>
    <%  } %>
</table>
       
    </div>
</body>
</html>