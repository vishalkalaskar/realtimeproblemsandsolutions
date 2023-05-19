package com.map.service.fileupload;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.fileupload.service.helper.FileUploadHelper;

@RestController
public class FileUploadController {

	@Autowired
	private FileUploadHelper  fileUploadHelper;
	
	@PostMapping("/dev/uploadfile") 
	  public ResponseEntity<Map<String,Object>> handleFileUpload(@RequestParam("file") MultipartFile file ) {

	    String fileName = file.getOriginalFilename();
	    try 
	    {
	     file.transferTo( new File("D:\\VishalK\\uploadfile" + fileName));
	      //boolean f= fileUploadHelper.uploadFile(file);
		  //System.out.println( "Value of F is   "+f);
	      System.out.println("file upload succefully");
	      System.out.println("file Name is "+fileName);
	      Map<String,Object> res = new HashMap<>();
	      res.put("success",true);
	      res.put("message", "File uploaded successfully");
	   // return ResponseEntity.ok("File uploaded successfully");
	     return ResponseEntity.ok(res);
	    }
	    catch (Exception e) 
	    {
	    	Map<String,Object> res = new HashMap<>();
		      res.put("success",false);
		      res.put("message", "File uploaded Error");
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	    } 
	  }

	
	@PostMapping(value="/dev/jsonFileUpload",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String,Object>> UPDfileUpload(@RequestParam("file") MultipartFile file){
		
		System.out.println("File name"+file.getOriginalFilename());
		System.out.println("File Size"+file.getSize());
		System.out.println("File ContentType"+file.getContentType());
		try {
			if (file.isEmpty()) {
				Map<String,Object> res = new HashMap<>();
			      res.put("success",false);
			      res.put("message", "Empty File,Please upload file with content");
		      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
          //return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Empty File,Please upload file with content");
   
			}
			if(!file.getContentType().equals("application/json")) {
				Map<String,Object> res = new HashMap<>();
			      res.put("success",false);
			      res.put("message", "Only Json File allowed");
		      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
				//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only Json File allowed");
			}
			
			boolean f= fileUploadHelper.uploadFile(file);
			System.out.println( "Value of F is   "+f);
			if(f) {
				System.out.println("File Uploaded successfuly ............");
				Map<String,Object> res = new HashMap<>();
			      res.put("success",true);
			      res.put("message", "File uploaded successfully");
			     return ResponseEntity.ok(res);
				//return ResponseEntity.ok("File Upload Successfully");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		Map<String,Object> res = new HashMap<>();
	      res.put("success",false);
	      res.put("message", "Something Went wrong Please try again");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went wrong Please try again");		
	}
	
	@PostMapping("/dev/updFileUpload")
	public ResponseEntity<Map<String,Object>>  JsonfileUpload(@RequestParam("updfile") MultipartFile file){
		
		System.out.println("File name"+file.getOriginalFilename());
		System.out.println("File Size"+file.getSize());
		System.out.println("File ContentType"+file.getContentType());
		try {
			if (file.isEmpty()) {
				Map<String,Object> res = new HashMap<>();
			      res.put("success",false);
			      res.put("message", "Empty File,Please upload file with content");
		      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
         //return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Empty File,Please upload file with content");
   
			}
			if(!file.getContentType().equals("text/csv")) {
				  Map<String,Object> res = new HashMap<>();
			      res.put("success",false);
			      res.put("message", "Only .CSV File allowed");
		          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
				//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only .CSV File allowed");
			}
			
			boolean f= fileUploadHelper.uploadFile(file);
			if(f) {
				System.out.println("File Uploaded successfuly ............");
				Map<String,Object> res = new HashMap<>();
			      res.put("success",true);
			      res.put("message", "File uploaded successfully");
			     return ResponseEntity.ok(res);
				//return	ResponseEntity.ok("File Upload Successfully");
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		Map<String,Object> res = new HashMap<>();
	      res.put("success",false);
	      res.put("message", "Something Went wrong Please try again");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went wrong Please try again");		
	}
	@PostMapping("/dev/txtFileUpload")
	public ResponseEntity<String>  plainTextfileUpload(@RequestParam("txtfile") MultipartFile file){
		
		System.out.println("File name"+file.getOriginalFilename());
		System.out.println("File Size"+file.getSize());
		System.out.println("File ContentType"+file.getContentType());
		try {
			if (file.isEmpty()) {
         return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Empty File,Please upload file with content");
   
			}
			if(!file.getContentType().equals("text/plain")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only plain text File allowed");
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