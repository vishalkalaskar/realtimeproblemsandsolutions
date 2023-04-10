var upload= angular.module('myapp', []);

upload.controller('userCtrl', ['$scope', '$http', function ($scope, $http) {
	
 $scope.upload = function(){
 
  var fd = new FormData();
  var files = document.getElementById('file').files[0];
  fd.append('file',files);

  // AJAX request
  $http({
   method: 'post',
   url: 'http://localhost:8088/uploadfile',
   data: fd,
   headers: {'Content-Type': undefined},
  }).then(function successCallback(response) { 
    // Store response data
    alert("File Uploaded Successfully");
    $scope.response = response.data;
  });
 }
 
}]);