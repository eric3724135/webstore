<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<link type="text/css" href="resource/css/nv.d3.css" rel="stylesheet" />
<script src="resource/js/d3.min.js"></script>
<script src="resource/js/nv.d3.min.js"></script>
<title>Insert title here</title>
<style>
#chart2 svg {
	height: 400px;
}
</style>
</head>
<body>
	<div id="chart2">
		<svg></svg>
	</div>
	<script type="text/javascript">
		d3.json("resource/pie.json", function(error, data) {
			nv.addGraph(function() {
				var chart = nv.models.pieChart().x(function(d) {
					return d.label
				}).y(function(d) {
					return d.value
				}).showLabels(true);

				d3.select("#chart2 svg").datum(data).transition().duration(1200)
						.call(chart);

				return chart;
			});
		});
	</script>
</body>
</html>