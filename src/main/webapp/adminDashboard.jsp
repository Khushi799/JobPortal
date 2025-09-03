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
</head>
<body>
    <h2>Admin Dashboard</h2>

    <!-- ✅ Logout button -->
    <form action="logout" method="get" style="float:right;">
        <input type="submit" value="Logout"/>
    </form>

    <a href="addJob.jsp">Add New Job</a>
    <br><br>

    <h3>All Jobs</h3>
    <table border="1">
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
                <form action="deleteJob" method="post">
                    <input type="hidden" name="jobId" value="<%= job.getId() %>"/>
                    <input type="submit" value="Delete"/>
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
    <table border="1">
        <tr>
            <th>ID</th><th>Job ID</th><th>User ID</th><th>Status</th>
        </tr>
        <%
            if (applications != null && !applications.isEmpty()) {
                for (Application app : applications) {
        %>
        <tr>
            <td><%= app.getId() %></td>
            <td><%= app.getJobId() %></td>
            <td><%= app.getUserId() %></td>
            <td><%= app.getStatus() %></td>
        </tr>
        <%      }
            } else {
        %>
        <tr><td colspan="4">No applications found.</td></tr>
        <%  } %>
    </table>
</body>
</html>
