package dto;

import java.sql.Timestamp;

public class CountryDTO {
	
	protected int country_id;
	protected String country;
	protected Timestamp last_update;
	
	
	public CountryDTO(int country_id, String country, Timestamp last_update) {
		super();
		this.country_id = country_id;
		this.country = country;
		this.last_update = last_update;
	}


	public int getCountry_id() {
		return country_id;
	}


	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public Timestamp getLast_update() {
		return last_update;
	}


	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}


}
