var apptwo=angular.module('apptwo', []);
apptwo.controller('twoCtrl', function($scope,$http) {
  //$scope.name = 'World';
  $scope.uploadFile = function() {
	  
    console.log($scope.jsonFile);
    	
		var fd = new FormData();
		fd.append('file', $scope.jsonFile);
        //console.log("fd"+fd);
		$scope.url = "http://localhost:8082/uploadfile";

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
			 //$scope.fileName=$scope.csvFile;
			 //console.log('File uploaded successfully:',response.data);
			 console.log('File uploaded successfully:');
			 console.log('File Name:',$scope.jsonFile);
		
			 //Reset the input file type
			  document.getElementById("jsonFile").value = null;
			// $scope.myForm.$setPristine();
			// $scope.myForm.$setUntouched();
		  //return response.data;
		}, function(error) {

			alert("Error uploading file");
			console.log('Error uploading file:', error);
		});
  };
}).directive('fileUpload', ['$parse', function($parse) {
  return {
    restrict: 'A',
    link: function(scope, element, attrs) {
      var model = $parse(attrs.fileUpload);
      var modelSetter = model.assign;

      element.bind('change', function() {
        scope.$apply(function() {
          console.log(element[0].files[0]);
          modelSetter(scope, element[0].files[0]);
        });
      });
    },
  };
}]);