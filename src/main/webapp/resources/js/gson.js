var busApp = angular.module('busApp', []);

busApp.controller('busCtrl', function($scope, $http) {

	$scope.changePage = function(pageNum) {
		$http.get('/webstore/gson/page?pageNum=' + pageNum).success(
				function(data) {
					$scope.buses = data;
				});
	};

});
