package com.example.auto.controller;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileDownloadController 
{
	@GetMapping("/download")
	public ResponseEntity<FileSystemResource> downloadFile()
	{
		String filepath="src/main/resources/csvfile/sanity.csv";
		File file = new File(filepath);
		if(file.exists()) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=sanity.csv");
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			FileSystemResource resource = new FileSystemResource(file);
			return ResponseEntity.ok().headers(headers).body(resource);
			
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
