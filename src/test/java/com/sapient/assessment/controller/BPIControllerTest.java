package com.sapient.assessment.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sapient.assessment.service.BPIService;

@WebMvcTest
@RunWith(SpringRunner.class)
public class BPIControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	BPIService bpiService;
	
	@Test
	public void getHistoricalBpiTest200Ok() throws Exception {
		mockMvc.perform(
                get("/api/bpi/historical")
                .param("start", "01-09-2013")
                .param("end", "05-09-2013")
                .param("currency", "USD")
        ).andExpect(
                status().isOk()
        );
		
		 verify(bpiService).getHistoricalBPI(any(), any(), anyString());
	}
	
	
}
