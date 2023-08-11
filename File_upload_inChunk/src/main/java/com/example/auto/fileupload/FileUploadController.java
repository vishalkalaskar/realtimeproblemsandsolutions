package com.example.auto.fileupload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	// private static final String UPLOAD_DIRECTORY = "D:\\Girish\\IVIDMS\\file\\";
	 private static final String UPLOAD_DIRECTORY = "/data/home/kpp/fota_tamp/";
	
	 private static final String UPLOAD_DIR = "/data/home/kpp/ividms/FotaFirmwareUpload/";
	// public  String UPLOAD_DIR="D:\\Girish\\IVIDMS\\Out\\";
	 

	 @PostMapping("/check")
	 public ResponseEntity<String> checkfile(@RequestParam("fileName") String fileName){
		 System.out.println("check funchion :-"+ fileName);
		 
		 try {
			  // File file = new File("D:\\Girish\\IVIDMS\\Out\\" + fileName);
			   File file = new File(UPLOAD_DIR + fileName);
	            if(file.exists())
	            {
	            	System.out.println("file:-"+ fileName);
	            	file.delete();
	            }
	            return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error ", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	 }


	    @PostMapping("/uploadana")
	    public ResponseEntity<?> uploadChunk(@RequestParam("chunk") MultipartFile chunk, @RequestParam("offset") long offset, @RequestParam("fileName") String fileName, @RequestParam("totalChunk") int totalChunk /*@RequestParam("fileName2") File fileName2*/) {
	        try {
	        	System.out.println("reading file chunk-"+offset);
	            File file = new File(UPLOAD_DIRECTORY + fileName);
	           
	            //second File Start
	          //  File file2= new File(UPLOAD_DIRECTORY + fileName2.getName());
	            
	            //-----
	            
	            //System.err.println("total chunk:-"+totalChunk+"offset:-"+offset+"chunk:-"+chunk);

	            // Generate a unique file name for each chunk
		        String originalFilename = chunk.getOriginalFilename();
		        
		        String uniqueFileName = String.format("%s.%04d", originalFilename, offset);
		        
		      //  String uniqueFileName = originalFilename + offset +  ".upd" ;
		     
	           // System.out.println("FIle UPDATE");
		        
		        File tempFile = new File(UPLOAD_DIRECTORY, uniqueFileName);
		        
		        // Save the chunk to the temporary file
		        try (InputStream inputStream = chunk.getInputStream()) {

		            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		        }
		        
		        // Check if all chunks have been uploaded        
		        System.out.println("offset:-"+offset+"tottalchunk:-"+totalChunk);
		       
		        File directoryPath = new File(UPLOAD_DIRECTORY);
	            //List of all the text files
	            File filesList[] = directoryPath.listFiles();
		        
	            System.out.println("length :-"+filesList.length);
		        if (filesList.length == totalChunk) {

		           // Arrays.sort(filesList);
		            System.out.println("List of the text files in the specified directory:");
		            List<File> files =new ArrayList<>();
		            for(File file1 : filesList) {
		               System.out.println("File name: "+file1.getName());
		               //System.out.println("File path: "+file1.getAbsolutePath());
		              // System.out.println("Size :"+file1.getTotalSpace());
		               //System.out.println(" ");
		               files.add(file1);
		            }

		           // File folder1 = new File("D:\\Girish\\IVIDMS\\Out\\" + fileName);
		            File folder1 = new File(UPLOAD_DIR + fileName);
		            
		            mergeFiles(files,folder1);
		            return ResponseEntity.ok("File uploaded successfully");
		           // return  new ResponseEntity<>("file Done",HttpStatus.ACCEPTED);

		        } else {

		            // Return the response with the status indicating the chunk has been uploaded successfully

		            return ResponseEntity.ok("Chunk uploaded successfully");

		        }
	            
	            
	        } catch (IOException e) {
	            return new ResponseEntity<>("Error uploading chunk", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    public static void mergeFiles(List<File> files, File into)
	            throws IOException {
	        try (FileOutputStream fos = new FileOutputStream(into);
	             BufferedOutputStream mergingStream = new BufferedOutputStream(fos)) {
	        	
	        	synchronized(FileUploadController.class) {
	            for (File f : files) {
	                Files.copy(f.toPath(), mergingStream);
	                f.delete();
	                
	            }
	        	  
//	            File directoryPath = new File(UPLOAD_DIRECTORY);
//	            for (File file3: Objects.requireNonNull(directoryPath.listFiles())) {
//
//	                if (!file3.isDirectory()) {
//
//	                    file3.delete();
//	                }
//	            }
	            
	        	   }
	            
	        }
	    }
}