package com.sapient.assessment.domain;

import java.util.Map;

public class BPIResponse {

	private Map<String, Double> bpi;
	
	private String disclaimer;
	
	private BPITimeResponse time;

	public Map<String, Double> getBpi() {
		return bpi;
	}

	public void setBpi(Map<String, Double> bpi) {
		this.bpi = bpi;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public BPITimeResponse getTime() {
		return time;
	}

	public void setTime(BPITimeResponse time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bpi == null) ? 0 : bpi.hashCode());
		result = prime * result + ((disclaimer == null) ? 0 : disclaimer.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BPIResponse other = (BPIResponse) obj;
		if (bpi == null) {
			if (other.bpi != null)
				return false;
		} else if (!bpi.equals(other.bpi))
			return false;
		if (disclaimer == null) {
			if (other.disclaimer != null)
				return false;
		} else if (!disclaimer.equals(other.disclaimer))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BPIResponse [bpi=" + bpi + ", disclaimer=" + disclaimer + ", time=" + time + "]";
	}
	
	
}
