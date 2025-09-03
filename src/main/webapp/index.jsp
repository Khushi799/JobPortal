<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Job Portal</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            font-family: Arial, sans-serif;
            background: url("img/skillify-bg.jpg") no-repeat center center fixed;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            background: rgba(128, 128, 128, 0.7); /* Grey with 70% opacity */
            padding: 40px;
            border-radius: 15px;
            text-align: center;
            box-shadow: 0px 4px 15px rgba(0,0,0,0.4);
        }

        h2 {
            color: white;
            font-size: 28px;
            margin-bottom: 20px;
            text-shadow: 1px 1px 6px rgba(0,0,0,0.6);
        }

        .btn {
            text-decoration: none;
            display: inline-block;
            padding: 12px 25px;
            margin: 10px;
            border-radius: 25px;
            font-size: 18px;
            font-weight: bold;
            transition: all 0.3s ease;
        }

        .btn-login {
            background: #ff9800;
            color: white;
        }
        .btn-login:hover {
            background: #e68900;
        }

        .btn-signup {
            background: #4caf50;
            color: white;
        }
        .btn-signup:hover {
            background: #3d8b40;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome to Skillify Job Portal</h2>
        <a href="login.jsp" class="btn btn-login">Login</a>
        <a href="signup.jsp" class="btn btn-signup">Signup</a>
    </div>
</body>
</html>