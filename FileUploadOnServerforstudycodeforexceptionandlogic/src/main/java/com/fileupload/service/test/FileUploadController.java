package com.fileupload.service.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.service.helper.FileUploadHelper;

@RestController
public class FileUploadController {

	@Autowired
	private FileUploadHelper  fileUploadHelper;
	@PostMapping(value="/jsonFileUpload",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String>  UPDfileUpload(@RequestParam("file") MultipartFile file){
		
		System.out.println("File name"+file.getOriginalFilename());
		System.out.println("File Size"+file.getSize());
		System.out.println("File ContentType"+file.getContentType());
		try {
			if (file.isEmpty()) {
         return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Empty File,Please upload file with content");
   
			}
			if(!file.getContentType().equals("application/json")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only Json File allowed");
			}
			
			boolean f= fileUploadHelper.uploadFile(file);
			System.out.println( "Value of F is   "+f);
			if(f) {
				System.out.println("File Uploaded successfuly ............");
				return ResponseEntity.ok("File Upload Successfully");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went wrong Please try again");		
	}
	
	@PostMapping("/updFileUpload")
	public ResponseEntity<String>  JsonfileUpload(@RequestParam("updfile") MultipartFile file){
		
		System.out.println("File name"+file.getOriginalFilename());
		System.out.println("File Size"+file.getSize());
		System.out.println("File ContentType"+file.getContentType());
		try {
			if (file.isEmpty()) {
         return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Empty File,Please upload file with content");
   
			}
			if(!file.getContentType().equals("text/csv")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only .CSV File allowed");
			}
			
			boolean f= fileUploadHelper.uploadFile(file);
			if(f) {
				System.out.println("File Uploaded successfuly ............");
				return	ResponseEntity.ok("File Upload Successfully");
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went wrong Please try again");		
	}
}