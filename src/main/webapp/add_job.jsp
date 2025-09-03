<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Post Job</title>
    <%@ include file="all_component/all_css.jsp" %>
</head>
<body style="background-color: #f0f1f2;">
    <%@ include file="all_component/navbar.jsp" %>

    <div class="container p-2">
        <div class="col-md-10 offset-md-1">
            <div class="card mt-3" style="box-shadow: 8px 9px 19px -10px rgba(33, 37, 41, 1);">
                <div class="card-body">
                    <div class="text-center text-success mb-3">
                        <i class="fa fa-user-friends fa-3x"></i>
                        <h5>Add Job</h5>
                    </div>

                    <!-- Success Message -->
                    <c:if test="${not empty succMsg}">
                        <div class="alert alert-success" role="alert">${succMsg}</div>
                        <c:remove var="succMsg" />
                    </c:if>

                    <!-- Job Posting Form -->
                    <form action="add_job" method="post">
                        <div class="form-group mb-3">
                            <label>Job Title</label>
                            <input type="text" class="form-control" name="title" placeholder="Enter job title" required />
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-4">
                                <label>Location</label>
                                <select name="location" class="form-control" required>
                                    <option value="" disabled selected>Choose...</option>
                                    <option value="Bangalore">Bangalore</option>
                                    <option value="Baroda">Baroda</option>
                                    <option value="Chandigarh">Chandigarh</option>
                                    <option value="Coimbatore">Coimbatore</option>
                                    <option value="Chennai">Chennai</option>
                                    <option value="Delhi-NCR">Delhi-NCR</option>
                                    <option value="Dhule">Dhule</option>
                                    <option value="Hyderabad">Hyderabad</option>
                                    <option value="Kolkata">Kolkata</option>
                                    <option value="Mumbai">Mumbai</option>
                                    <option value="Pune">Pune</option>
                                    <option value="Shirpur">Shirpur</option>
                                </select>
                            </div>

                            <div class="col-md-4">
                                <label>Category</label>
                                <select name="category" class="form-control" required>
                                    <option value="" disabled selected>Choose...</option>
                                    <option value="IT">IT</option>
                                    <option value="Developer">Developer</option>
                                    <option value="Banking">Banking</option>
                                    <option value="Engineer">Engineer</option>
                                    <option value="Teacher">Teacher</option>
                                </select>
                            </div>

                            <div class="col-md-4">
                                <label>Status</label>
                                <select name="status" class="form-control" required>
                                    <option value="Active">Active</option>
                                    <option value="Inactive">Inactive</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group mb-3">
                            <label>Job Description</label>
                            <textarea name="desc" class="form-control" rows="6" placeholder="Enter job description" required></textarea>
                        </div>

                        <button type="submit" class="btn btn-success w-100">Publish Job</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
