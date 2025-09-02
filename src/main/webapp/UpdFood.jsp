<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Food </title>
</head>
<body>
<div style="width:30%;margin:50px auto;">
<h2 class="text-primary">UPDATE FOOD</h2>
	<form action="${pageContext.request.contextPath}/FoodUpdServe" method="post">
	
	
		
		<input type="text" class="form-control" name="fname"  placeholder="ENTER YOUR FOOD NAME"/>
		
		<input type="text" class="form-control" name="price"  placeholder="ENTER YOUR FOOD PRICE"/>
		<input type="text" class="form-control" name="fid"    placeholder="ENTER THE FOOD ID"/>
	 		 <input type="submit" class="btn btn-outline-danger" value="UPDATE"/>
	</form>
</div>
</body>
</html>