var brick = angular.module('brick', []);

brick
		.controller(
				'brickCtrl',
				function($scope, $http) {
					$scope.getUserHash = function() {
						$http
								.get(
										'http://brickset.com/api/v2.asmx/login?apiKey=83qE-3s8K-00kU&username=eric3724135&password=eric26495')
								.success(
										function(data) {
											$scope.userHash = data;
											console.log($scope.userHash);
											$http
													.get(
															'http://brickset.com/api/v2.asmx/getSets?apiKey=83qE-3s8K-00kU&userHash='
																	+ $scope.userHash
																	+ '&query=&theme=&subtheme=&setNumber=&year=2015&owned=&wanted=&orderBy=&pageSize=&pageNumber=&userName=')
													.success(function(data) {
														$scope.sets = data;
														console.log($scope.sets);
													});
										});
					};
				});