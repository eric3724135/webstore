<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
<script src="/webstore/resource/js/gson.js"></script>

<title>Kao Bus List</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Kao Bus List</h1>
			</div>
		</div>
	</section>
	<div class="row" ng-app="busApp">

		<div class="col-md-2"></div>
		<div class="col-md-8" ng-controller="busCtrl"
			ng-init="changePage('1')">
			<div class="col-md-12 text-center">
				<nav>
					<ul class="pagination text-center">
						<li><a href="#" aria-label="Previous"> <span
								aria-hidden="true">&laquo;</span>
						</a></li>
						<li><a href="#">1</a></li>
						<li><a ng-click="changePage('2')">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#" aria-label="Next"> <span
								aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>
			</div>

			<table id="content" class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>RouteID</th>
						<th>Bus Speed</th>
						<th>Longitude</th>
						<th>Latitude</th>
						<th>BusID</th>
					</tr>
				</thead>
				<tbody>

					<tr ng-repeat="bus in buses">
						<td>{{bus.routeID}}</td>
						<td>{{bus.speed}}</td>
						<td>{{bus.longitude}}</td>
						<td>{{bus.latitude}}</td>
						<td>{{bus.busID}}</td>
					</tr>
				</tbody>
			</table>

			<!-- 			<table id="content" class="table table-hover table-bordered"> -->
			<!-- 				<thead> -->
			<!-- 					<tr> -->
			<!-- 						<th>RouteID</th> -->
			<!-- 						<th>Bus Speed</th> -->
			<!-- 						<th>Longitude</th> -->
			<!-- 						<th>Latitude</th> -->
			<!-- 						<th>BusID</th> -->
			<!-- 					</tr> -->
			<!-- 				</thead> -->
			<!-- 				<tbody> -->
			<%-- 					<c:forEach items="${buses}" var="bus"> --%>
			<!-- 						<tr> -->
			<%-- 							<td>${bus.routeID}</td> --%>
			<%-- 							<td>${bus.speed}</td> --%>
			<%-- 							<td>${bus.longitude}</td> --%>
			<%-- 							<td>${bus.latitude}</td> --%>
			<%-- 							<td>${bus.busID}</td> --%>
			<!-- 						</tr> -->
			<%-- 					</c:forEach> --%>
			<!-- 				</tbody> -->
			<!-- 			</table> -->
		</div>
		<div class="col-md-2"></div>
	</div>
	<script type="text/javascript">
		$("#content tbody").on("click", "tr",function() {
			console.log($(this).text());
		});
		// 		$('#content').find('tr').click(
		// 				function() {
		// 					alert('You clicked row ' + ($(this).index() + 1)
		// 							+ JSON.stringify($(this)));
		// 				});
	</script>

</body>
</html>