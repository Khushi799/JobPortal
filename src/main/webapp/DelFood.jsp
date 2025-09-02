<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <jsp:include page="nav.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Food</title>
</head>
<body>
<div style="width:30%;margin:50px auto;">
<h2 class="text-primary">DELETE FOOD</h2>
	<form action="${pageContext.request.contextPath}/FoodDeleteServe" method="post">
	
		<input type="text" class="form-control" name="fid"  placeholder="ENTER THE FOOD ID"/>
		
	 		 <input type="submit" class="btn btn-outline-danger" value="DELETE"/>
	</form>
</div>
</body>
</html>