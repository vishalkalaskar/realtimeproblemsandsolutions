var app = angular.module('app', []);
app.controller('MainCtrl', function($scope, $http) {

	$scope.file = null;
	$scope.filename='';
	$scope.uploadfile = function(element)
	 {

		//alert("uploadfile function in execution");
		var input = document.getElementById("file");
		$scope.file = input.files[0];
         $scope.filename = element.files[0].name;
         //alert("filename "+ $scope.filename);
		console.log("this file type " + $scope.file);

	}
	
    $scope.submitForm = function() {
		
	
		var fd = new FormData();
		fd.append('file', $scope.file);

		$scope.url = "http://localhost:8087/uploadfile";

		$http({

			method: 'POST',
			url: $scope.url,
			headers: { 'Content-Type': undefined},
			data: fd,
			transformRequest: angular.identity,
		    //responseType:'text'

		}).then(function(response) {

			 alert("file uploaded successfully");
			 //content only display after file submiting...
			 $scope.fileName=$scope.filename;
			 //console.log('File uploaded successfully:',response.data);
			 console.log('File uploaded successfully:');
			 console.log('File Name:',$scope.filename);
		
			 //Reset the input file type
			 document.getElementById("file").value = null;
			 $scope.myForm.$setPristine();
			 $scope.myForm.$setUntouched();
		  //return response.data;
		}, function(error) {

			alert("Error uploading file");
			console.log('Error uploading file:', error);
		});
	}
	/*-----------------------to study the code---------------*/
		/*$scope.submitForm = function() {
		var fd = new FormData();
		fd.append('file', $scope.file);

		$scope.url = "http://localhost:8087/uploadfile";

		$http({
			method: 'POST',
			url: $scope.url,
			headers: { 'Content-Type': undefined },
			data: fd,
			transformRequest: angular.identity
		}).then(function(response) {
			if (response.data) {
				try {
					var data = JSON.parse(response.data);
					if (data.success) {
						alert("File uploaded successfully");
						console.log('File uploaded successfully:', data.message);
					} else {
						alert("Error uploading file: " + data.message);
						console.log('Error uploading file:', data.message);
					}
				} catch (e) {
					alert("Error parsing JSON response: " + e.message);
					console.log('Error parsing JSON response:', e.message);
					console.log('Response:', response.data);
				}
			} else {
				alert("Empty response received");
				console.log('Empty response received');
			}
		}, function(error) {
			alert("Error uploading file: " + error.message);
			console.log('Error uploading file:', error.message);
		});
	};*/
	
/*-----------------------to study the code---------------*/	


	/*$scope.uploadfile = function() {

alert("uploadfile function in execution");

 

var fd = new FormData();

fd.append('file', $scope.file);

//alert(fd);
  var fd = new FormData();
  var files = document.getElementById('file').files[0];
  fd.append('file',files);
 

$scope.url="http://localhost:8087/uploadfile"

$http({

method: 'POST',

url: $scope.url,

headers: {'Content-Type':undefined},

data: fd,

//application/json

transformRequest: angular.identity

}).then(function(response) {

alert("File uploaded successfully");

console.log('File uploaded successfully:', response.data);

}, function(error) {

alert("Error uploading file");

console.log('Error uploading file:', error);

});

 

}*/

});