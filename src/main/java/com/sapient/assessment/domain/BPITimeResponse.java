package com.sapient.assessment.domain;

public class BPITimeResponse {

	private String updated;
	private String updatedISO;
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getUpdatedISO() {
		return updatedISO;
	}
	public void setUpdatedISO(String updatedISO) {
		this.updatedISO = updatedISO;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		result = prime * result + ((updatedISO == null) ? 0 : updatedISO.hashCode());
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
		BPITimeResponse other = (BPITimeResponse) obj;
		if (updated == null) {
			if (other.updated != null)
				return false;
		} else if (!updated.equals(other.updated))
			return false;
		if (updatedISO == null) {
			if (other.updatedISO != null)
				return false;
		} else if (!updatedISO.equals(other.updatedISO))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BPITimeResponse [updated=" + updated + ", updatedISO=" + updatedISO + "]";
	}
	
	
	
}
