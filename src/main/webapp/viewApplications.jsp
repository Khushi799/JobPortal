<%@ page import="java.util.*, master.dto.Application" %>

<%
    // ✅ Ensure user is logged in
    if (session == null || session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Application> apps = (List<Application>) request.getAttribute("applications");
%>

<html>
<head>
    <title>My Applications</title>
    <style>
        table { border-collapse: collapse; width: 70%; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h2>My Job Applications</h2>

    <a href="userDashboard.jsp">Back to Dashboard</a>
    <br><br>

    <%
        if (apps != null && !apps.isEmpty()) {
    %>
    <table>
        <tr>
            <th>Application ID</th>
            <th>Job Title</th>
            <th>Status</th>
        </tr>
        <%
            for (Application app : apps) {
        %>
        <tr>
            <td><%= app.getId() %></td>
            <td><%= app.getJobTitle() != null ? app.getJobTitle() : app.getJobId() %></td>
            <td><%= app.getStatus() %></td>
        </tr>
        <% } %>
    </table>
    <%
        } else {
    %>
        <p>No applications found.</p>
    <% } %>

</body>
</html>
