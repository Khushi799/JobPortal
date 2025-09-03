<%@ page import="java.util.*, master.dto.Job" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eaf4fb; /* lighter blue shade */
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 900px;
            margin: 50px auto;
            background: rgba(255, 255, 255, 0.95);
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.15);
        }

        h2, h3 {
            text-align: center;
            color: #2b4c7e; /* softer blue tone */
        }

        .logout-btn {
            float: right;
            background: #6fa8dc; /* lighter blue button */
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 6px;
            cursor: pointer;
        }

        .logout-btn:hover {
            background: #3d85c6; /* hover slightly darker */
        }

        .search-form {
            text-align: center;
            margin: 20px 0;
        }

        .search-form input[type="text"] {
            padding: 8px;
            margin: 5px;
            border: 1px solid #bbb;
            border-radius: 6px;
            width: 200px;
        }

        .search-form input[type="submit"] {
            padding: 8px 16px;
            background: #6fa8dc;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .search-form input[type="submit"]:hover {
            background: #3d85c6;
        }

        .job-card {
            background: #f5faff;
            border: 1px solid #cce0f5;
            margin: 10px 0;
            padding: 15px;
            border-radius: 8px;
        }

        .job-card b {
            color: #2b4c7e;
        }

        .apply-btn {
            padding: 6px 12px;
            background: #77dd77; /* light green for contrast */
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .apply-btn:hover {
            background: #4caf50;
        }

        .link {
            display: block;
            text-align: center;
            margin-top: 20px;
            font-weight: bold;
            color: #2b4c7e;
            text-decoration: none;
        }

        .link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="logout" method="get" style="float:right;">
            <input type="submit" value="Logout" class="logout-btn"/>
        </form>
        <h2>Welcome to User Dashboard</h2>

        <!-- Search form -->
        <form action="${pageContext.request.contextPath}/searchJobs" method="get" class="search-form">
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
            <div class="job-card">
                <b><%= job.getTitle() %></b> - <%= job.getCompanyId() %>
                <form action="jobApply.jsp" method="post" style="display:inline;">
                    <input type="hidden" name="jobId" value="<%= job.getId() %>">
                    <input type="submit" value="Apply" class="apply-btn">
                </form>
            </div>
        <% 
                }
            } else { 
        %>
            <p style="text-align:center;">No jobs to display. Use the search above to find jobs.</p>
        <% } %>

        <a href="${pageContext.request.contextPath}/viewApplications" class="link">View My Applications</a>
    </div>
</body>
</html>