package com.example.auto.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import au.com.bytecode.opencsv.CSVWriter;

@Configuration
@EnableScheduling
public class AutoController 
{
	
	@Scheduled(fixedRate =60000) //schedule to execute at 9pm every day
	public void triggerCodeExcutionforfivesecond()
	{
		System.out.println("Executing code..every 1 second");
		String filePath ="src/main/resources/csvfile/sanity.csv";
		//String filePath ="D:\\csvfile";
		//String filePath ="src/main/resources/output.csv";
		//String filePath ="classpath:output.csv";
		File dirc = new File(filePath).getParentFile();
		if(!dirc.exists())
		{
			dirc.mkdir();
		}
		try(CSVWriter writer = new CSVWriter(new FileWriter(filePath,false)))
		{
			String[] headersone= {"  ","   "," ","  CVDAP Monitoring PROD   "};
			writer.writeNext(headersone);
			
			String[] headers= {"Sr.No","Category","Date","Time","Telemetry","Event","Alert","Raw CAN","Decoded Can","ISO Decoded-can","Command Histroy","Complex Event","ICB-Generic-can",
					"PvGenericCan","PvDiagnosticCan","ChargingTele","Health Monitoring","SCVEV can","CVICB ISO DTC CAN","TcuLog","Download Job","7daysDownload"};
							
			writer.writeNext(headers);
			
			String[] Row1= {"1","vin","",""};
			//String[] Row2= {"smith Anee","32","JohnAnn@gmail.com"};
			//String[] Row3= {"worse Anee","32","JohnAnn@gmail.com"};
			writer.writeNext(Row1);
			//writer.writeNext(Row2);
			//writer.writeNext(Row3);
			System.out.println("CSV file created successfully. at this \"src/main/resources/sanity.csv\"");
			
		}
		catch(IOException e) {
			System.out.println("Error writing to csv file :"+e.getMessage());
		}
		
	}

/*	@Scheduled(fixedRate =60000) //schedule to execute at 9pm every day
	public void triggerCodeExcutionforfivesecond()
	{
		System.out.println("Executing code..every 1 second");
		try {
		String filePath ="classpath:output.csv";
		//File dirc = new File(filePath).getParentFile();
		File dirc = ResourceUtils.getFile(filePath);
		if(!dirc.exists())
		{
			dirc.mkdir();
		}
		try(CSVWriter writer = new CSVWriter(new FileWriter(filePath)))
		{
			String[] headers= {"Name","Age","Email"};
			writer.writeNext(headers);
			
			String[] Row1= {"john Anee","32","JohnAnn@gmail.com"};
			String[] Row2= {"smith Anee","32","JohnAnn@gmail.com"};
			String[] Row3= {"worse Anee","32","JohnAnn@gmail.com"};
			writer.writeNext(Row1);
			writer.writeNext(Row2);
			writer.writeNext(Row3);
			System.out.println("CSV file created successfully.");
			
	    	}
		catch(IOException e) {
			System.out.println("Error writing to csv file :"+e.getMessage());
		}
		}
		catch(IOException e) {
			System.out.println("Error resolivng file path :"+e.getMessage());
		}
	}
	*/	
		

	@Scheduled(cron="0 0 21 * * ?") //schedule to execute at 9pm every day
	public void triggerCodeExcution()
	{
		
		System.out.println("Executing code..");
		
	}
}
