package com.example.demo.controller;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RecursionController 
{
  @GetMapping("/functionone")
  public String functionem()
  {
	  String url ="https://dummy.restapiexample.com/api/v1/employees";
	  RestTemplate restt= new RestTemplate();
	  HttpHeaders headers = new HttpHeaders();
	  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	  HttpEntity <String> entity =new HttpEntity<String>(headers);
	  System.out.println("this first calling");
	  return restt.exchange(url, HttpMethod.GET, entity, String.class).getBody();
  }
  
  @GetMapping("/functiontwo")
  public String functitwom()
  {
	  String url ="https://reqres.in/api/users";
	  RestTemplate restt= new RestTemplate();
	  HttpHeaders headers = new HttpHeaders();
	  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	  HttpEntity <String> entity =new HttpEntity<String>(headers);
	  System.out.println("this second calling");
	  return restt.exchange(url, HttpMethod.GET, entity, String.class).getBody();
  }
}
