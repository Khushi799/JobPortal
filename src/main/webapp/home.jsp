<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="master.dto.Jobs"%>
<%@page import="master.utilities.ConnectionFactory"%>
<%@page import="master.dao.JobDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User : Home</title>
<%@include file="all_component/all_css.jsp"%>
</head>

<body style="background-color: #d5d5d6;">

    <!-- Redirect if user not logged in -->
    <c:if test="${empty userobj}">
        <c:redirect url="login.jsp"/>
    </c:if> 

    <%@include file="all_component/navbar.jsp"%>

    <div class="container mb-5">
        <div class="row">
            <div class="col-md-12">

                <h5 class="text-center text-dark">All Jobs</h5>

                <c:if test="${not empty succMsg}">
                    <h4 class="text-center text-success">${succMsg}</h4>
                    <c:remove var="succMsg"/>
                </c:if>

                <!-- Search Card -->
                <div class="card bg-dark text-white mb-4">
                    <div class="card-body">
                        <form class="row g-3" action="more_view.jsp" method="get">
                            <!-- Location -->
                            <div class="col-md-5">
                                <label class="form-label text-white">Location</label>
                                <select name="loc" class="form-select bg-light text-dark border border-dark">
                                    <option selected>Choose...</option>
                                    <option value="Chandigarh">Chandigarh</option>
                                    <option value="Bangalore">Bangalore</option>
                                    <option value="Coimbatore">Coimbatore</option>
                                    <option value="Chennai">Chennai</option>
                                    <option value="Mumbai">Mumbai</option>
                                    <option value="Pune">Pune</option>
                                    <option value="Kolkata">Kolkata</option>
                                    <option value="Delhi-NCR">Delhi-NCR</option>
                                    <option value="Baroda">Baroda</option>
                                    <option value="Hyderabad">Hyderabad</option>
                                </select>
                            </div>

                            <!-- Category -->
                            <div class="col-md-5">
                                <label class="form-label text-white">Category</label>
                                <select name="cat" class="form-select bg-light text-dark border border-dark">
                                    <option selected>Choose...</option>
                                    <option value="IT">IT</option>
                                    <option value="Developer">Developer</option>
                                    <option value="Banking">Banking</option>
                                    <option value="Engineer">Engineer</option>
                                    <option value="Teacher">Teacher</option>
                                </select>
                            </div>

                            <!-- Button -->
                            <div class="col-md-2 d-flex align-items-end">
                                <button class="btn btn-outline-light w-100"><strong>Search</strong></button>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- Job Cards -->
                <%
                    JobDAO dao = new JobDAO(ConnectionFactory.getConnection());
                    List<Jobs> list = dao.getAllJobs();
                    for (Jobs j : list) {
                %>
                <div class="card mt-3 bg-light text-dark shadow-sm">
                    <div class="card-body">
                        <div class="text-center text-secondary mb-2">
                            <i class="fa-regular fa-clipboard fa-2x"></i>
                        </div>

                        <h6 class="fw-bold"><%= j.getTitle() %></h6>

                        <p>
                        <%
                            if (j.getDescription() != null && j.getDescription().length() > 0) {
                                if (j.getDescription().length() < 120) {
                                    out.print(j.getDescription());
                                } else {
                                    out.print(j.getDescription().substring(0, 120) + "...");
                                }
                            }
                        %>
                        </p>

                        <div class="row mb-2">
                            <div class="col-md-3">
                                <input type="text" class="form-control form-control-sm" 
                                       value="Location: <%= j.getLocation() %>" readonly>
                            </div>
                            <div class="col-md-3">
                                <input type="text" class="form-control form-control-sm" 
                                       value="Category: <%= j.getCategory() %>" readonly>
                            </div>
                        </div>

                        <h6 class="mt-2">Publish Date: <%= j.getPdate() %></h6>

                        <div class="text-center mt-3">
                            <a href="one_view.jsp?id=<%= j.getId() %>" 
                               class="btn btn-outline-secondary btn-sm w-50">View More</a>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>

            </div>
        </div>
    </div>

    <%@include file="all_component/footer.jsp"%>
</body>
</html>
