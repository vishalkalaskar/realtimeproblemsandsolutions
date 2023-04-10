package com.example.demo.controller;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class SpringFileUploadController {

//,produces = MediaType.MULTIPART_FORM_DATA_VALUE
  //@PostMapping(value="/uploadfile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
  @PostMapping("/uploadfile") 
  public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file ) {

    String fileName = file.getOriginalFilename();
    try {
      file.transferTo( new File("D:\\upload\\" + fileName));
      System.out.println("file upload succefully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } 
    return ResponseEntity.ok("File uploaded successfully.");
  }

}
