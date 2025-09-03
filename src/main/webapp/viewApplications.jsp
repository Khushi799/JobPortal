<%@ page import="java.util.*, master.dto.Application" %>

<%
    // ✅ Ensure user is logged in
    if (session == null || session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Application> apps = (List<Application>) request.getAttribute("applications");
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Applications</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eaf4fb; /* light blue background */
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 900px;
            margin: 60px auto;
            background: rgba(255, 255, 255, 0.95);
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.15);
        }

        h2 {
            text-align: center;
            color: #2b4c7e; /* soft navy blue */
            margin-bottom: 20px;
        }

        .back-link {
            display: inline-block;
            margin-bottom: 20px;
            text-decoration: none;
            color: #6fa8dc;
            font-weight: bold;
        }

        .back-link:hover {
            text-decoration: underline;
            color: #3d85c6;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 15px;
            background: #f9fbfe;
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            border: 1px solid #d6e4f0;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #6fa8dc;
            color: white;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f0f6fc;
        }

        tr:hover {
            background-color: #d9ecff;
        }

        p {
            text-align: center;
            color: #555;
            font-size: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>My Job Applications</h2>

        <a href="userDashboard.jsp" class="back-link">Back to Dashboard</a>

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
    </div>
</body>
</html>