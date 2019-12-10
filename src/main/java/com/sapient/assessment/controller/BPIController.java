package com.sapient.assessment.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.assessment.dto.ResponseDTO;
import com.sapient.assessment.service.BPIService;

@RestController
@RequestMapping("/api/bpi")
public class BPIController {

	@Autowired
	BPIService bpiService;

	@GetMapping("/historical")
	public ResponseEntity<ResponseDTO> getHistoricalBPI(
			@RequestParam("start") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
			@RequestParam("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate,
			@RequestParam(value = "currency", required = false, defaultValue = "USD") String currency) {

		ResponseDTO response = bpiService.getHistoricalBPI(startDate, endDate, currency);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	@GetMapping("/downloadfiles")
	public ResponseEntity<String> downLoadFiles(
			) {

		String response = bpiService.downloadFiles();
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
