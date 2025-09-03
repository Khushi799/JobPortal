<html>
<head>
    <title>Apply for Job</title>
</head>
<body>
    <h2>Apply for Job</h2>

    <form action="${pageContext.request.contextPath}/applyJob" method="post">
        <input type="hidden" name="jobId" value="<%= request.getParameter("jobId") %>">
        <label>Why should we hire you?</label><br>
        <textarea name="coverLetter" rows="5" cols="40"></textarea><br><br>
        <input type="submit" value="Apply">
    </form>

</body>
</html>
