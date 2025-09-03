<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Signup</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #1e3c72, #2a5298); /* Blue gradient */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .signup-box {
            background: rgba(128, 128, 128, 0.85); /* Grey with opacity */
            padding: 30px;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.4);
            width: 350px;
        }
        h2 {
            color: white;
            margin-bottom: 20px;
        }
        input, select {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: none;
        }
        input[type="submit"] {
            background-color: #ff9800; /* Orange */
            color: white;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #e68900;
        }
        p {
            color: white;
            margin-top: 15px;
        }
        a {
            color: #4caf50; /* Green */
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="signup-box">
        <h2>Signup Form</h2>
        <form action="SignupServlet" method="post">
            <input type="text" name="name" placeholder="Full Name" required><br>
            <input type="email" name="email" placeholder="Email" required><br>
            <input type="password" name="password" placeholder="Password" required><br>
            <input type="text" name="qualification" placeholder="Qualification" required><br>
            <select name="role" required>
                <option value="seeker">Seeker</option>
                <option value="admin">Admin</option>
            </select><br>
            <input type="submit" value="Signup">
        </form>
        <p>Already have an account? <a href="login.jsp">Login here</a></p>
    </div>
</body>
</html>