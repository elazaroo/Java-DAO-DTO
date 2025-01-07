package dto;

import java.sql.Timestamp;

public class CategoryDTO {
	
	protected int category_id;
	protected String name;
	protected Timestamp last_update;
	
	
	public CategoryDTO(int category_id, String name, Timestamp last_update) {
		super();
		this.category_id = category_id;
		this.name = name;
		this.last_update = last_update;
	}


	public int getId() {
		return category_id;
	}


	public void setId(int id) {
		this.category_id = id;
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
