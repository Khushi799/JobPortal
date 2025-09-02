<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>
<%@include file="all_component/all_css.jsp"%>
<style>
.back-img {
	background: url("img/editProfile.jpg");
	height: 100vh;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
</head>
<body style="background-color: #f0f1f2;">
	<%@include file="all_component/navbar.jsp"%>

	<div class="container-fluid back-img">
		<div class="row p-4">
			<div class="col-md-4 offset-md-4">
				<div class="card bg-dark text-white opacity-75" style="box-shadow: 8px 9px 19px -10px rgba(33, 37, 41, 1);">
					<div class="card-body">
						<div class="text-center">
							<i class="fa-solid fa-user-pen fa-2x" aria-hidden="true"></i>
							<h5>Edit Profile</h5>
						</div>
						
						<form action="update_profile" method="post">
							<input type="hidden" name="id" value="${userobj.id}">
							
							<div class="form-group mb-3 mt-2">
								<label>Enter Full Name</label>
								<input type="text" placeholder="Name" required class="form-control"
									name="name" value="${userobj.name}">
							</div>

							<div class="form-group mb-3">
								<label>Qualification</label>
								<input type="text" placeholder="Qualification" required class="form-control"
									name="qualification" value="${userobj.qualification}">
							</div>

							<div class="form-group mb-3">
								<label>Email</label>
								<input type="email" placeholder="Email" required class="form-control"
									name="email" value="${userobj.email}">
							</div>

							<div class="form-group mb-3">
								<label>Phone</label>
								<input type="text" placeholder="Phone Number" required class="form-control"
									name="phone" value="${userobj.phone}">
							</div>

							<div class="form-group mb-3">
								<label>Password</label>
								<input type="password" placeholder="Password" class="form-control"
									name="password">
								<small class="text-light">Leave blank if you don’t want to change the password</small>
							</div>

							<div class="text-center mt-4">
								<button type="submit" class="btn btn-primary">Update Profile</button>
							</div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="all_component/footer.jsp"%>
</body>
</html>
