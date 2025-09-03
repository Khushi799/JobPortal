<%@ page import="java.util.List" %>
<%@ page import="com.dto.Job" %>
<%@ page import="com.dto.Application" %>

<%
    List<Job> jobs = (List<Job>) request.getAttribute("jobs");
    List<Application> applications = (List<Application>) request.getAttribute("applications");
%>

<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
    <h2>Admin Dashboard</h2>
    
    <a href="addJob.jsp">Add New Job</a>
    <br><br>

    <h3>All Jobs</h3>
    <table border="1">
        <tr>
            <th>ID</th><th>Title</th><th>Category</th><th>Location</th><th>Salary</th><th>Action</th>
        </tr>
        <%
            if(jobs != null) {
                for(Job job : jobs) {
        %>
        <tr>
            <td><%=job.getId()%></td>
            <td><%=job.getTitle()%></td>
            <td><%=job.getCategory()%></td>
            <td><%=job.getLocation()%></td>
            <td><%=job.getSalary()%></td>
            <td>
                <form action="deleteJob" method="post">
                    <input type="hidden" name="jobId" value="<%=job.getId()%>"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
        <%      }
            }
        %>
    </table>

    <h3>Applications</h3>
    <table border="1">
        <tr>
            <th>ID</th><th>Job Title</th><th>User</th><th>Status</th>
        </tr>
        <%
            if(applications != null) {
                for(Application app : applications) {
        %>
        <tr>
            <td><%=app.getId()%></td>
            <td><%=app.getJobTitle()%></td>
            <td><%=app.getUserName()%></td>
            <td><%=app.getStatus()%></td>
        </tr>
        <%      }
            }
        %>
    </table>
</body>
</html>
