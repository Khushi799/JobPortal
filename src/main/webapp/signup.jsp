<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Signup</title>
</head>
<body>
    <h2>Signup Form</h2>
    <form action="SignupServlet" method="post">
        Name: <input type="text" name="name" required><br><br>
        Email: <input type="email" name="email" required><br><br>
        Password: <input type="password" name="password" required><br><br>
        Qualification: <input type="text" name="qualification" required><br><br>
        Role:
        <select name="role" required>
            <option value="seeker">Seeker</option>
            <option value="admin">Admin</option>
        </select><br><br>
        <input type="submit" value="Signup">
    </form>
    <p>Already have an account? <a href="login.jsp">Login here</a></p>
</body>
</html>
