package com.sapient.assessment.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Downloader {

	@GetMapping("/download")
	public String downloadfile() {

//		List<String> keys = new ArrayList<>();
//		keys.add("Nuveen or TIAA");
//
//		List<String> countries = new ArrayList<>();
//		countries.add("US");
		
		String key="(NUVEEN or TIAA)";
		String country="US";

		// Dates
		Calendar cal = (Calendar) Calendar.getInstance();
		System.out.println(cal.getTime());
		
		System.out.println(cal.getTime());

		Calendar endcal = (Calendar) Calendar.getInstance();
		endcal.add(Calendar.YEAR, -1);
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");  
		  System.out.println(formatter.format(cal.getTime()));

		 RestTemplate restTemplate = new RestTemplate();
		try {
			System.out.println(cal.compareTo(endcal));
			while (cal.compareTo(endcal) > 0) {
				String endDate=formatter.format(cal.getTime());
				cal.add((Calendar.DATE), -4);
				String startDate=formatter.format(cal.getTime());
				
				
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
				HttpEntity<String> entity = new HttpEntity<>(headers);
				ResponseEntity<byte[]> response = restTemplate.exchange(
						"https://api.gdeltproject.org/api/v2/doc/doc?query="+key
						+"sourcecountry:"+country+"&sourcelang:eng&mode=ArtList&maxrecords=250&sort=DateDesc&startdatetime="+startDate
						+"&enddatetime="+endDate+"&format=csv",
						HttpMethod.GET, entity, byte[].class);
				Files.write(Paths.get("C:\\Users\\Sujit\\hackathon\\files/response_"+startDate+".csv"), response.getBody());
				
				System.out.println("Downloded for "+ cal.getTime());
				
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "downloaded";
	}

}
