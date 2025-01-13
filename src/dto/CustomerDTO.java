package dto;

import java.sql.Timestamp;

public class CustomerDTO {
	
	protected int customer_id;
	protected int store_id;
	protected String first_name;
	protected String last_name;
	protected String email;
	protected int address_id;
	protected int active;
	protected Timestamp create_date;
	protected Timestamp last_update;
	
	
	public CustomerDTO(int store_id, String first_name, String last_name, String email, int address_id, int active,
			Timestamp create_date, Timestamp last_update) {
		super();
		this.store_id = store_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.address_id = address_id;
		this.active = active;
		this.create_date = create_date;
		this.last_update = last_update;
	}
	
	public CustomerDTO(int customer_id, int store_id, String first_name, String last_name, String email, int address_id, int active,
			Timestamp create_date, Timestamp last_update) {
		super();
		this.customer_id = customer_id;
		this.store_id = store_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.address_id = address_id;
		this.active = active;
		this.create_date = create_date;
		this.last_update = last_update;
	}


	public int getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}


	public int getStore_id() {
		return store_id;
	}


	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getAddress_id() {
		return address_id;
	}


	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}


	public Timestamp getCreate_date() {
		return create_date;
	}


	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}


	public Timestamp getLast_update() {
		return last_update;
	}


	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}

	
}
