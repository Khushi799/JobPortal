<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<c:if test="${empty userobj or userobj.role ne 'admin'}">
		<c:redirect url="login.jsp" />
	</c:if>
	<%@include file="all_component/navbar.jsp"%>

	<div class="container p-2">
		<div class="col-md-10 offset-md-1">
			<div class="card mt-3" style="box-shadow: 8px 9px 19px -10px rgba(33, 37, 41, 1);">
				<div class="card-body">
					<div class="text-center text-success">
						<i class="fa fa-user-friends fa-3x"></i>
						<%
							int id = Integer.parseInt(request.getParameter("id"));
							JobDAO dao = new JobDAO(DBConnect.getconn());
							Jobs j = dao.getJobById(id);
						%>
						<h5>Edit Job</h5>
					</div>

					<form action="update" method="post">
						<input type="hidden" value="<%=j.getId()%>" name="id">

						<div class="form-group">
							<label>Enter Title</label>
							<input type="text" required class="form-control" name="title" value="<%=j.getTitle()%>">
						</div>

						<div class="form-row">
							<div class="form-group col-md-4 mt-3">
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
