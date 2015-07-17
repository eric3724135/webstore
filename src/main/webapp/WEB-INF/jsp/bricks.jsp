<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
<script src="/webstore/resource/js/brick.js"></script>

<title>Products</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>LEGOs</h1>
				<p>LEGO BrickSet</p>
			</div>
		</div>
	</section>

	<section class="container">
		<!-- 		<div ng-controller="brickCtrl" ng-init="getUserHash()"> -->
		<!-- 			<a class="btn btn-danger pull-left" ng-click="getUserHash()"> <span -->
		<!-- 				class="glyphicon glyphicon-remove-sign"></span> Clear Cart </a> -->
		<!-- 		</div> -->
		<div class="row">
			<c:forEach items="${bricks}" var="brick">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">
						<img class="img-responsive"
							src="<c:url value='${brick.imageURL}'></c:url>" alt="image"
							style="width: 100%;visibility:${brick.image?'none':'hidden'};" />
						<div class="caption">
							<h3>${brick.number}</h3>
							<p>${brick.name}</p>
							<p>${brick.usRetailPrice}USD</p>
							<p>
								<a href="<spring:url value=''/> " class="btn btn-primary"> <span
									class="glyphicon-info-sign glyphicon" /></span> Details
								</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>