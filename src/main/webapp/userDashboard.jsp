<%@ page import="java.util.*, master.dto.Job" %>
<html>
<head>
    <title>User Dashboard</title>
</head>
<body>
    <h2>Welcome to User Dashboard</h2>

    <!-- Logout button -->
    <form action="logout" method="get" style="float:right;">
        <input type="submit" value="Logout"/>
    </form>
    <br><br>

    <!-- Search form -->
    <form action="${pageContext.request.contextPath}/searchJobs" method="get">
    <input type="text" name="location" placeholder="Search by location">
    <input type="text" name="category" placeholder="Search by category">
    <input type="submit" value="Search Jobs">
</form>
    <h3>Available Jobs</h3>
    <%
        List<Job> jobs = (List<Job>) request.getAttribute("jobs");
        if (jobs != null && !jobs.isEmpty()) {
            for (Job job : jobs) {
    %>
        <div>
            <b><%= job.getTitle() %></b> - <%= job.getCompanyId() %>
            [ 
              <form action="jobApply.jsp" method="post" style="display:inline;">
                  <input type="hidden" name="jobId" value="<%= job.getId() %>">
                  <input type="submit" value="Apply">
              </form>
            ]
        </div>
    <% 
            }
        } else { 
    %>
        <p>No jobs to display. Use the search above to find jobs.</p>
    <% } %>

    <br>
    <a href="${pageContext.request.contextPath}/viewApplications">View My Applications</a>
</body>
</html>