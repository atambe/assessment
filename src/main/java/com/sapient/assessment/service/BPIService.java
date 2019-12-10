package com.sapient.assessment.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

	public ResponseDTO getHistoricalBPI(final Date start, final Date end, final String currency) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URI)
				.queryParam("start", DateUtils.dateToString(start)).queryParam("end", DateUtils.dateToString(end))
				.queryParam("currency", currency);

		BPIResponse response = restTemplate.getForObject(builder.build().encode().toUriString(), BPIResponse.class);

		Map<String, Double> map = response.getBpi();

		return processResponse(response, currency);
	}

	private ResponseDTO processResponse(final BPIResponse response, final String currency) {
		Map<String, Double> bpis = response.getBpi();

		List<BpiDTO> bpiList = new ArrayList<>();

		bpis.entrySet().stream().parallel()
				.forEach(e -> bpiList.add(new BpiDTO(DateUtils.parseDate(e.getKey()), e.getValue())));

		ResponseDTO responseDto = new ResponseDTO();
		responseDto.setBpi(bpiList);
		responseDto.setHighest(bpis.get(Collections.max(bpis.entrySet(), Map.Entry.comparingByValue()).getKey()));
		responseDto.setCurrency(currency);

		return responseDto;
	}

	public String downloadFiles(String key, String country) {
		System.out.println("Started Downloading files for key : " + key + " Country : " + country);
		Calendar cal = (Calendar) Calendar.getInstance();
		Calendar endcal = (Calendar) Calendar.getInstance();
		endcal.add(Calendar.YEAR, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String folString = key.replaceAll("[^a-zA-Z0-9]", "");
		String folCountry = country.replaceAll("[^a-zA-Z0-9]", "");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		/*
		 * ResponseEntity<byte[]> response = restTemplate
		 * .exchange("https://api.gdeltproject.org/api/v2/doc/doc?query=(Nuveen OR TIAA) sourcecountry:US&sourcelang:eng&mode=ArtList&maxrecords=250&sort=DateDesc&timespan=200d&format=csv"
		 * , HttpMethod.GET, entity, byte[].class);
		 * Files.write(Paths.get("/Volumes/sandbox/hackathon/TIAA/Files/response.csv"),
		 * response.getBody());
		 */

		while (cal.compareTo(endcal) > 0) {
			String endDate = formatter.format(cal.getTime());
			cal.add((Calendar.DATE), -4);
			String startDate = formatter.format(cal.getTime());
			try {
				ResponseEntity<byte[]> responseFile = restTemplate
						.exchange(
								"https://api.gdeltproject.org/api/v2/doc/doc?query=" + key + "&sourcecountry:" + country
										+ "&sourcelang:eng&mode=ArtList&maxrecords=250&sort=DateDesc&startdatetime="
										+ startDate + "&enddatetime=" + endDate + "&format=csv",
								HttpMethod.GET, entity, byte[].class);
				String path = "/Volumes/sandbox/hackathon/TIAA/Files/" + folString + "/" + folCountry;
				File file = new File(path);
				file.mkdirs();
		
				System.out.println(path + "/response_" + startDate + ".csv");
				Files.write(Paths.get(path + "/response_" + startDate + ".csv"), responseFile.getBody());

				System.out.println("Downloded for " + cal.getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		System.out.println("Completed Downloading files for key : " + key + " Country : " + country);
		return "downloaded";
	}

}
