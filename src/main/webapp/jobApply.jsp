<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Apply for Job</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eaf4fb; /* light blue background */
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
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

        label {
            font-weight: bold;
            color: #333;
        }

        input[type="text"], input[type="email"], input[type="number"], textarea {
            width: 100%;
            border-radius: 6px;
            border: 1px solid #ccc;
            padding: 10px;
            margin-top: 8px;
            margin-bottom: 20px;
            font-size: 14px;
        }

        textarea {
            resize: vertical;
        }

        input[type="submit"] {
            background: #6fa8dc; /* pastel blue */
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 15px;
        }

        input[type="submit"]:hover {
            background: #3d85c6; /* darker blue on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Apply for Job</h2>

        <form action="${pageContext.request.contextPath}/applyJob" method="post">
            <input type="hidden" name="jobId" value="<%= request.getParameter("jobId") %>">

            <label>Email</label><br>
            <input type="email" name="email" placeholder="Enter your email" required><br>

            <label>Qualification</label><br>
            <input type="text" name="qualification" placeholder="Enter your highest qualification" required><br>

            <label>Work Experience (in years)</label><br>
            <input type="number" name="experience" placeholder="Enter years of experience" min="0" required><br>

            <label>Why should we hire you?</label><br>
            <textarea name="coverLetter" rows="5" placeholder="Write your cover letter here..." ></textarea><br>

            <input type="submit" value="Apply">
        </form>
    </div>
</body>
</html>
