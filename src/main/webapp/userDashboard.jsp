<%@ page import="java.util.*,master.dto.Job" %>
<html>
<head>
    <title>User Dashboard</title>
</head>
<body>
    <h2>Welcome to User Dashboard</h2>

    <!-- Logout button -->
    <form action="logout" method="get" style="float:right;">
        <input type="submit" value="Logout">
    </form>
    <br><br>

    <form action="searchJobs" method="get">
        <input type="text" name="location" placeholder="Search by location">
        <input type="text" name="category" placeholder="Search by category">
        <input type="submit" value="Search Jobs">
    </form>

    <h3>Available Jobs</h3>
    <%
        List<Job> jobs = (List<Job>) request.getAttribute("jobs");
        if(jobs != null){
            for(Job job : jobs){
    %>
        <div>
            <b><%= job.getTitle() %></b> - <%= job.getCompanyId() %>  
            [<a href="jobApply.jsp?jobId=<%=job.getId()%>">Apply</a>]
        </div>
    <% }} %>

    <br>
    <a href="viewApplications">View My Applications</a>
</body>
</html>
