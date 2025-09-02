<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.entity.Jobs"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.dao.JobDAO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Job</title>
<%@include file="all_component/all_css.jsp"%>
</head>
<body style="background-color: #f0f1f2;">

<%
    if(userobj == null || !"admin".equals(userobj.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%@include file="all_component/navbar.jsp"%>

<div class="container p-2">
    <div class="col-md-10 offset-md-1">
        <div class="card mt-3" style="box-shadow: 8px 9px 19px -10px rgba(33, 37, 41, 1);">
            <div class="card-body text-center text-success">
                <i class="fa fa-user-friends fa-3x"></i>
                <%
                    int id = Integer.parseInt(request.getParameter("id"));
                    JobDAO dao = new JobDAO(DBConnect.getconn());
                    Jobs j = dao.getJobById(id);
                %>
                <h5>Edit Job</h5>

                <form action="update" method="post">
                    <input type="hidden" value="<%=j.getId()%>" name="id">

                    <div class="form-group mt-3">
                        <label>Enter Title</label>
                        <input type="text" required class="form-control" name="title" value="<%=j.getTitle()%>">
                    </div>

                    <div class="form-row mt-3">
                        <div class="form-group col-md-4">
                            <label>Location</label>
                            <select name="location" class="custom-select" required>
                                <option value="<%=j.getLocation()%>" selected><%=j.getLocation()%></option>
                                <option value="Chandigarh">Chandigarh</option>
                                <option value="Bangalore">Bangalore</option>
                                <option value="Coimbatore">Coimbatore</option>
                                <option value="Chennai">Chennai</option>
                                <option value="Mumbai">Mumbai</option>
                                <option value="Pune">Pune</option>
                                <option value="Kolkata">Kolkata</option>
                                <option value="Delhi-NCR">Delhi-NCR</option>
                                <option value="Baroda">Baroda</option>
                            </select>
                        </div>
                    </div>

                    <div class="text-center mt-4">
                        <button type="submit" class="btn btn-primary">Update Job</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>

<%@include file="all_component/footer.jsp"%>
</body>
</html>
