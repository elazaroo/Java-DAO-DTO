package dto;

import java.sql.Timestamp;

public class InventoryDTO {
	
	protected int inventory_id;
	protected int film_id;
	protected int store_id;
	protected Timestamp last_update;
	
	
	public InventoryDTO(int inventory_id, int film_id, int store_id, Timestamp last_update) {
		super();
		this.inventory_id = inventory_id;
		this.film_id = film_id;
		this.store_id = store_id;
		this.last_update = last_update;
	}


	public int getInventory_id() {
		return inventory_id;
	}


	public void setInventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}


	public int getFilm_id() {
		return film_id;
	}


	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}


	public int getStore_id() {
		return store_id;
	}


	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}


	public Timestamp getLast_update() {
		return last_update;
	}


	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	
	
	

}
