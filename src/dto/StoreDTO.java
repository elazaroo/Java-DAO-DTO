package dto;

import java.sql.Timestamp;

public class StoreDTO {
	
	protected int store_id;
	protected int manager_staff_id;
	protected int address_id;
	protected Timestamp last_update;
	
	
	public StoreDTO(int store_id, int manager_staff_id, int address_id, Timestamp last_update) {
		super();
		this.store_id = store_id;
		this.manager_staff_id = manager_staff_id;
		this.address_id = address_id;
		this.last_update = last_update;
	}


	public int getStore_id() {
		return store_id;
	}


	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}


	public int getManager_staff_id() {
		return manager_staff_id;
	}


	public void setManager_staff_id(int manager_staff_id) {
		this.manager_staff_id = manager_staff_id;
	}


	public int getAddress_id() {
		return address_id;
	}


	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}


	public Timestamp getLast_update() {
		return last_update;
	}


	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	
	

}
