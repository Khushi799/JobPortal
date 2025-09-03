<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
        .login-box {
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
        input {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: none;
        }
        input[type="submit"] {
            background-color: #4caf50; /* Green */
            color: white;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #3d8b40;
        }
        p {
            color: white;
            margin-top: 15px;
        }
        a {
            color: #ff9800; /* Orange */
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-box">
        <h2>Login Form</h2>
        <form action="LoginServlet" method="post">
            <input type="email" name="email" placeholder="Email" required><br>
            <input type="password" name="password" placeholder="Password" required><br>
            <input type="submit" value="Login">
        </form>
        <p>Don’t have an account? <a href="signup.jsp">Signup here</a></p>
    </div>
</body>
</html>