<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="nav.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Food Add Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>

</head>
<body>
<div style="width:30%;margin:50px auto;">
<h2 class="text-primary">ADD FOOD</h2>
	<form action="${pageContext.request.contextPath}/FoodAddServe" method="post">
	
	
		<input type="text" class="form-control" name="fid"  placeholder="ENTER THE FOOD ID"/>
		<input type="text" class="form-control" name="fname" placeholder="ENTER YOUR FOOD NAME"/>
		
		<input type="text" class="form-control" name="price" placeholder="ENTER YOUR FOOD PRICE"/>
	 		 <input type="submit" class="btn btn-outline-danger" value="ADD"/>
	</form>
</div>
</body>
</html>