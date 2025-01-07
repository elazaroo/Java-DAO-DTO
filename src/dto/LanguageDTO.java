package dto;

import java.sql.Timestamp;

public class LanguageDTO {
	
	protected int language_id;
	protected String name;
	protected Timestamp last_update;
	
	
	public LanguageDTO(int language_id, String name, Timestamp last_update) {
		super();
		this.language_id = language_id;
		this.name = name;
		this.last_update = last_update;
	}


	public int getLanguage_id() {
		return language_id;
	}


	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Timestamp getLast_update() {
		return last_update;
	}


	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	
	

}
