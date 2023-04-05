var app = angular.module('app', []);


app.controller('MainCtrl', function($scope, $http) {


	$scope.myvalue = "";
	var function1Flag = false;
	var function2Flag = false;

	$scope.functionone = function() {
		$scope.myvalue = "hello from appjs";
		//alert("function One execution");
		if (!function1Flag) {
			function1Flag = true;
			$http({
				method: "GET",
				url: "http://localhost:8093/functionone"
			}).then(function mySuccess(response) {
				alert("function one url call");
				function2Flag = false; // Reset function2 flag before calling 
				$scope.functiontwo();
				console.log("this first url");
				$scope.myWelcome = response.data;
			}, function myError(response) {
				
				$scope.myWelcome = response.statusText;
			});

		}


	}
	$scope.functiontwo = function() {
		//alert("function two execution");
		if (!function2Flag) {
			function2Flag = true;
			$http({
				method: "GET",
				url: "http://localhost:8093/functiontwo"
			}).then(function mySuccess(response) {
			    alert("function two url call");
				function1Flag = false; // Reset function1 flag before calling 
				$scope.functionone();
				console.log("this second url");
				$scope.myWelcome = response.data;
			}, function myError(response) {
				
				$scope.myWelcome = response.statusText;
			});
			
		}

	}

});