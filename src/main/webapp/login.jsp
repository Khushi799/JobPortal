<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
<jsp:include page="all_component/navbar.jsp"/>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

<style>
    body {
        background: #f4f7f8;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    .login-card {
        background: #fff;
        padding: 30px 25px;
        border-radius: 12px;
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
        width: 100%;
        max-width: 400px;
    }

    .login-card h3 {
        text-align: center;
        margin-bottom: 20px;
        color: #333;
    }

    .login-card .form-control {
        border-radius: 8px;
    }

    .login-card button {
        border-radius: 25px;
    }

    .alert-message {
        text-align: center;
        margin-bottom: 15px;
        font-weight: 500;
    }
</style>
</head>
<body>

<div class="login-card">

    <h3><i class="fa-solid fa-user"></i> User Login</h3>

    <!-- Success Message -->
    <c:if test="${not empty succMsg}">
        <div class="alert alert-success alert-message">
            ${succMsg}
        </div>
        <c:remove var="succMsg"/>
    </c:if>

    <!-- Error Message -->
    <c:if test="${not empty errMsg}">
        <div class="alert alert-danger alert-message">
            ${errMsg}
        </div>
        <c:remove var="errMsg"/>
    </c:if>

    <form action="login" method="post">
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
        </div>
        <button type="submit" class="btn btn-success w-100">Login</button>
    </form>

    <p class="text-center mt-3">
        Don't have an account? <a href="signup.jsp">Sign Up</a>
    </p>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
