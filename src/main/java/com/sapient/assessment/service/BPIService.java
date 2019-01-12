package com.sapient.assessment.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sapient.assessment.domain.BPIResponse;
import com.sapient.assessment.dto.BpiDTO;
import com.sapient.assessment.dto.ResponseDTO;
import com.sapient.assessment.utils.DateUtils;

@Service
public class BPIService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${api.bpi.historical.url}")
	private String URI;

	public ResponseDTO getHistoricalBPI(final Date start, final Date end , final String currency ) {
		
		
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URI)
				 .queryParam("start", DateUtils.dateToString(start))
				 .queryParam("end", DateUtils.dateToString(end))
				 .queryParam("currency", currency);
               		
		
		BPIResponse response = restTemplate.getForObject(builder.build().encode().toUriString(), BPIResponse.class);
		
		Map<String, Double> map = response.getBpi();
		
		return processResponse(response, currency);
	}
	
	private ResponseDTO processResponse(final BPIResponse response, final String currency ) {
		Map<String, Double> bpis = response.getBpi();
		
		List<BpiDTO> bpiList = new ArrayList<>();
		
		bpis.entrySet().stream().parallel().forEach(e ->  
					bpiList.add(new BpiDTO( DateUtils.parseDate(e.getKey()) , e.getValue()))
		);
		
		ResponseDTO responseDto = new ResponseDTO();
		responseDto.setBpi(bpiList);
		responseDto.setHighest(bpis.get(Collections.max(bpis.entrySet(), Map.Entry.comparingByValue()).getKey()));
		responseDto.setCurrency(currency);
		
		return responseDto;
	}
	
	

}
