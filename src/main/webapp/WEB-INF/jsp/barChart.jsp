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
#chart svg {
	height: 400px;
}
</style>
</head>
<body>

	<div id="chart">
		<svg></svg>
	</div>
	<script>
		var tickMarks = [ 250000, 500000, 750000, 1000000, 1250000, 1500000,
				1750000, 2000000 ]
		d3.json("resource/data.json", function(error, data) {
			nv.addGraph(function() {
				var chart = nv.models.linePlusBarChart().margin({
					top : 30,
					right : 60,
					bottom : 50,
					left : 70
				}).x(function(d, i) {
					return i
				}).y(function(d) {
					return d[1]
				}).color(d3.scale.category10().range()).focusEnable(false);

				chart.xAxis.tickFormat(function(d) {
					var dx = data[0].values[d] && data[0].values[d][0] || 0;
					return d3.time.format('%x')(new Date(dx))
				});

				chart.y1Axis.tickValues(tickMarks).tickFormat(d3.format(',f'));

				chart.y2Axis.tickValues(tickMarks).tickFormat(d3.format(',f'));
				chart.lines.forceY([ 0, 2000000 ]);
				chart.bars.forceY([ 0, 2000000 ]).padData(false);

				d3.select('#chart svg').datum(data).transition().duration(0)
						.call(chart);

				nv.utils.windowResize(chart.update);

				return chart;
			});

		});
	</script>
</body>
</html>