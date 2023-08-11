
angular.module('fotafile', [])
    .controller('fotaUploadController', ['$scope', function ($scope) {


/*Start*/
$scope.checkFileExtention = function() {
	 var fileInput = document.getElementById('fileInput');                
   	 var filePath = fileInput.value;
         
            // Allowing file type
            var allowedExtensions =  /(\.upd|\.json)$/i;
             
            if (!allowedExtensions.exec(filePath)) {
                alert('Invalid file type');
                fileInput.value = '';
                return false;
            }
            else
            {
            $scope. checkFile();
            }
        }
	
/*ENd*/	
   $scope.checkFile = function() {
	  // alert("hello")
	   const fileInput = document.getElementById('fileInput');
         const files = fileInput.files;
          let currentFileIndexFile = 0;
		   const file = files[currentFileIndexFile];

		     const deleteFile = new FormData();
          deleteFile.append('fileName', file.name);
		   
		   const xhr = new XMLHttpRequest();
      xhr.open('POST', '/check', true);
      xhr.onload = function () {
        if (xhr.status === 200) {
          console.log(' file delete successfully');
          $scope.uploadFile();
        } else {
          console.error('Error uploading chunk');
        }
      };
	xhr.send(deleteFile);  
	   }
        
       
   $scope.uploadFile = function() {
	  // alert("dggdgdg")
	  
      const fileInput = document.getElementById('fileInput');
      const files = fileInput.files;
      
      //second File Start
     //  const fileInput2 = document.getElementById('file');
	 	// const files2 = fileInput2.files;
	  //second File End
	  
      let totalSize = 0;
      let uploadedSize = 0;

      for (let i = 0; i < files.length; i++) {
        totalSize += files[i].size;
      }
	
	
      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const fileSize = file.size;
        //
         const chunkSize =900 * 1024; // 1MB
        var totalChunks = Math.ceil(file.size / chunkSize);
        var chuncIntial =0;
        //
       
        let offset = 0;
      
        while (offset < fileSize) {
          const chunk = file.slice(offset, offset + chunkSize);
         
//var totalChunks = Math.ceil(file.size / chunkSize);
             
          const formData = new FormData();
          formData.append('chunk', chunk);
          formData.append('offset', chuncIntial);
          formData.append('fileName', file.name);
          //second File Start
        //  formData.append('fileName2', files2);
          //second File End
           formData.append('totalChunk', totalChunks);

           sendChunk(formData);
           chuncIntial++;
          offset += chunkSize;
          uploadedSize += chunkSize;

          const progress = Math.floor((uploadedSize / totalSize) * 100);
          
          
      // const progress = Math.floor((uploadedSize * 100 ) /totalSize);
        //  updateProgressBar(progress);
       // move(progress);
        
     //  console.log(JSON.stringify(progress));
        }
      }
    }

    function sendChunk(formData) {
      const xhr = new XMLHttpRequest();
      xhr.open('POST', '/uploadana', true);
      xhr.onload = function () {
		
        if (xhr.status === 200) {		
			console.table("Send chunk:-"+formData);
          console.log('Chunk uploaded successfully'); 
         //move();
        } else {
          console.error('Error uploading chunk');
        }
        
         /* if(xhr.status==202){
			//alert("hello");
			 var fileInput = document.getElementById('fileInput');                
   			fileInput.value = '';
		} */
 
      };

      xhr.send(formData);
          
   
    }

//progress
/*var i = 0;
function move() {
  if (i == 0) {
    i = 1;
    var elem = document.getElementById("myBar");
    var width = 10;
    var id = setInterval(frame, 10);
    function frame() {
      if (width >= 100) {
        clearInterval(id);
        i = 0;
      } else {
        width++;
        elem.style.width = width + "%";
        elem.innerHTML = width  + "%";
      }
    }
  }
}*/


/*    function updateProgressBar(progress) {
      const myProgress = document.getElementById('myBar');
     // const progressT = document.getElementById('progressT'); 
      progressBar.style.width = progress + '%';
    //  progressT.innerText = progress + '%';
    }*/
    
    }]);
