package dto;

import java.sql.Timestamp;

public class Film_ActorDTO {
	
	protected int actor_id;
	protected int film_id;
	protected Timestamp last_update;
	
	
	public Film_ActorDTO(int actor_id, int film_id, Timestamp last_update) {
		super();
		this.actor_id = actor_id;
		this.film_id = film_id;
		this.last_update = last_update;
	}


	public int getActor_id() {
		return actor_id;
	}


	public void setActor_id(int actor_id) {
		this.actor_id = actor_id;
	}


	public int getFilm_id() {
		return film_id;
	}


	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}


	public Timestamp getLast_update() {
		return last_update;
	}


	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	
	

}
