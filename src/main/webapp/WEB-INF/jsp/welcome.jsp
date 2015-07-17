<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>${greeting}</h1>
				<p>${tagline}</p>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<div class="col-md-4 ">
				<%@ include file="/WEB-INF/jsp/barChart.jsp"%>
			</div>
			<div class="col-md-4 ">
				<%@ include file="/WEB-INF/jsp/barChart.jsp"%>
			</div>
			<div class="col-md-4 ">
				<%@ include file="/WEB-INF/jsp/pieChart.jsp"%>
			</div>
		</div>
	</section>
</body>
</html>