<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Job Portal</title>
<%@include file="all_component/all_css.jsp"%>

<style type="text/css">
.back-img {
    background: url("img/Home_page.jpg");
    width: 100%;
    height: 100vh;
    background-repeat: no-repeat;
    background-size: cover;
}

.footerImg {
    width: 100%;
}
</style>
</head>

<body>
    <%@include file="all_component/navbar.jsp"%>

    <div class="container-fluid back-img">
        <div class="text-center">
            <h1 class="text-black p-4">
                <i class="fa fa-book" aria-hidden="true"></i> Online Job Portal
            </h1>
        </div>
    </div>

    <img class="footerImg" alt="" src="img/img1.png">
    <img class="footerImg" alt="" src="img/img2.png">
    <img class="footerImg" alt="" src="img/footer.png">

</body>
</html>
