package com.sapient.assessment.dto;

import java.util.List;

public class ResponseDTO {

	private List<BpiDTO> bpi;
	
	private Double highest;
	
	private String currency;

	public List<BpiDTO> getBpi() {
		return bpi;
	}

	public void setBpi(List<BpiDTO> bpi) {
		this.bpi = bpi;
	}

	public Double getHighest() {
		return highest;
	}

	public void setHighest(Double highest) {
		this.highest = highest;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "ResponseDTO [bpi=" + bpi + ", highest=" + highest + ", currency=" + currency + "]";
	}
	
	
	
	
}
