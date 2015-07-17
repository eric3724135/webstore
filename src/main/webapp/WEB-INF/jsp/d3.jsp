<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset="utf-8">
<style>
.counties {
	fill: none;
}

.states {
	fill: none;
	stroke: #fff;
	stroke-linejoin: round;
}

.q0-9 {
	fill: rgb(247, 251, 255);
}

.q1-9 {
	fill: rgb(222, 235, 247);
}

.q2-9 {
	fill: rgb(198, 219, 239);
}

.q3-9 {
	fill: rgb(158, 202, 225);
}

.q4-9 {
	fill: rgb(107, 174, 214);
}

.q5-9 {
	fill: rgb(66, 146, 198);
}

.q6-9 {
	fill: rgb(33, 113, 181);
}

.q7-9 {
	fill: rgb(8, 81, 156);
}

.q8-9 {
	fill: rgb(8, 48, 107);
}
</style>
<body>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/queue-async/1.0.7/queue.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/topojson/1.6.19/topojson.min.js"></script>
	<script>
	
		var width = 960, height = 600;

		var rateById = d3.map();

		var quantize = d3.scale.quantize().domain([ 0, .15 ]).range(
				d3.range(9).map(function(i) {
					return "q" + i + "-9";
				}));

		var projection = d3.geo.albersUsa().scale(1280).translate(
				[ width / 2, height / 2 ]);

		var path = d3.geo.path().projection(projection);

		var svg = d3.select("body").append("svg").attr("width", width).attr(
				"height", height);

		queue().defer(d3.json, "/mbostock/raw/4090846/us.json").defer(d3.tsv,
				"unemployment.tsv", function(d) {
					rateById.set(d.id, +d.rate);
				}).await(ready);

		function ready(error, us) {
			if (error)
				throw error;

			svg.append("g").attr("class", "counties").selectAll("path").data(
					topojson.feature(us, us.objects.counties).features).enter()
					.append("path").attr("class", function(d) {
						return quantize(rateById.get(d.id));
					}).attr("d", path);

			svg.append("path").datum(
					topojson.mesh(us, us.objects.states, function(a, b) {
						return a !== b;
					})).attr("class", "states").attr("d", path);
		}

		d3.select(self.frameElement).style("height", height + "px");
	</script>