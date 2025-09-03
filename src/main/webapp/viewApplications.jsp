<%@ page import="java.util.*,master.dto.Application" %>
<html>
<head>
    <title>My Applications</title>
</head>
<body>
    <h2>My Job Applications</h2>

    <%
        List<Application> apps = (List<Application>) request.getAttribute("applications");
        if(apps != null && !apps.isEmpty()){
            for(Application app : apps){
    %>
        <div>
            Job ID: <%= app.getJobId() %> | Status: <%= app.getStatus() %>
        </div>
    <% }} else { %>
        <p>No applications found.</p>
    <% } %>
</body>
</html>
