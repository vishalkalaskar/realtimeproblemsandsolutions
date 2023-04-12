package com.example.demo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class SpringFileUploadController {

//,produces = MediaType.MULTIPART_FORM_DATA_VALUE
  //@PostMapping(value="/uploadfile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
 /* @PostMapping("/uploadfile") 
  public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file ) {

    String fileName = file.getOriginalFilename();
    try 
    {
      file.transferTo( new File("D:\\uploadone\\" + fileName));
      System.out.println("file upload succefully");
      System.out.println("file Name is "+fileName);
   // return ResponseEntity.ok("File uploaded successfully");
     return ResponseEntity.ok().body("File uploaded successfully");
    }
    catch (Exception e) 
    {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } 
  }*/

	 @PostMapping("/uploadfile") 
	  public ResponseEntity<Map<String,Object>> handleFileUpload(@RequestParam("file") MultipartFile file ) {

	    String fileName = file.getOriginalFilename();
	    try 
	    {
	      file.transferTo( new File("D:\\uploadone\\" + fileName));
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

}
