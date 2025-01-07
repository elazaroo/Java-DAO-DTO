package dto;

import java.sql.Timestamp;

public class Film_CategoryDTO {
	
	protected int film_id;
	protected int category_id;
	protected Timestamp last_update;
	
	
	public Film_CategoryDTO(int film_id, int category_id, Timestamp last_update) {
		super();
		this.film_id = film_id;
		this.category_id = category_id;
		this.last_update = last_update;
	}


	public int getFilm_id() {
		return film_id;
	}


	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}


	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public Timestamp getLast_update() {
		return last_update;
	}


	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	

}
