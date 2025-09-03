<%@ page import="java.util.*,com.dto.ApplicationDTO" %>
<html>
<head>
    <title>My Applications</title>
</head>
<body>
    <h2>My Job Applications</h2>

    <%
        List<ApplicationDTO> apps = (List<ApplicationDTO>) request.getAttribute("applications");
        if(apps != null && !apps.isEmpty()){
            for(ApplicationDTO app : apps){
    %>
        <div>
            Job ID: <%= app.getJobId() %> | Status: <%= app.getStatus() %>
        </div>
    <% }} else { %>
        <p>No applications found.</p>
    <% } %>
</body>
</html>
