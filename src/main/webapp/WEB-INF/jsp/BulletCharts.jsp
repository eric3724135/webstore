<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<style type="text/css">
body {
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	margin: auto;
	padding-top: 40px;
	position: relative;
	width: 960px;
}

button {
	position: absolute;
	right: 10px;
	top: 10px;
}

.bullet {
	font: 10px sans-serif;
}

.bullet .marker {
	stroke: #000;
	stroke-width: 2px;
}

.bullet .tick line {
	stroke: #666;
	stroke-width: .5px;
}

.bullet .range.s0 {
	fill: #eee;
}

.bullet .range.s1 {
	fill: #ddd;
}

.bullet .range.s2 {
	fill: #ccc;
}

.bullet .measure.s0 {
	fill: lightsteelblue;
}

.bullet .measure.s1 {
	fill: steelblue;
}

.bullet .title {
	font-size: 14px;
	font-weight: bold;
}

.bullet .subtitle {
	fill: #999;
}
</style>
</head>
<body>
	<script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>
	<script src="/webstore/resource/js/bullet.js"></script>

	<script type="text/javascript">
		var margin = {
			top : 5,
			right : 40,
			bottom : 20,
			left : 120
		}, width = 960 - margin.left - margin.right, height = 50 - margin.top
				- margin.bottom;

		var chart = d3.bullet().width(width).height(height);

		var jsonStr = '';

		$(document).ready(function() {

			$.ajax({
				url : '/webstore/gson/d3/BulletData',
				type : "GET",
				dataType : 'json',
				success : function(data, textStatus) {
					jsonStr = data;
					console.log(jsonStr);

				},

				error : function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status);
					alert(thrownError);
				}
			});
		});

// 		var jsonData = $.parseJSON(jsonStr);
		
		d3
				.json(jsonStr,
						function(error, data) {
							if (error)
								throw error;
							var svg = d3.select("body").selectAll("svg").data(
									data).enter().append("svg").attr("class",
									"bullet").attr("width",
									width + margin.left + margin.right).attr(
									"height",
									height + margin.top + margin.bottom)
									.append("g").attr(
											"transform",
											"translate(" + margin.left + ","
													+ margin.top + ")").call(
											chart);

							var title = svg.append("g").style("text-anchor",
									"end").attr("transform",
									"translate(-6," + height / 2 + ")");

							title.append("text").attr("class", "title").text(
									function(d) {
										return d.title;
									});

							title.append("text").attr("class", "subtitle")
									.attr("dy", "1em").text(function(d) {
										return d.subtitle;
									});

							d3.selectAll("button").on(
									"click",
									function() {
										svg.datum(randomize).call(
												chart.duration(1000)); // TODO automatic transition
									});
						});

		function randomize(d) {
			if (!d.randomizer)
				d.randomizer = randomizer(d);
			d.ranges = d.ranges.map(d.randomizer);
			d.markers = d.markers.map(d.randomizer);
			d.measures = d.measures.map(d.randomizer);
			return d;
		}

		function randomizer(d) {
			var k = d3.max(d.ranges) * .2;
			return function(d) {
				return Math.max(0, d + k * (Math.random() - .5));
			};
		}
	</script>
</body>

</html>
