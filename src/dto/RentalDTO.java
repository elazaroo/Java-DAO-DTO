package dto;

import java.sql.Timestamp;

public class RentalDTO {
	
	protected int rental_id;
	protected Timestamp rental_date;
	protected int inventory_id;
	protected int customer_id;
	protected Timestamp return_date;
	protected int staff_id;
	protected Timestamp last_update;
	
	
	public RentalDTO(int rental_id, Timestamp rental_date, int inventory_id, int customer_id, Timestamp return_date, int staff_id,
			Timestamp last_update) {
		super();
		this.rental_id = rental_id;
		this.rental_date = rental_date;
		this.inventory_id = inventory_id;
		this.customer_id = customer_id;
		this.return_date = return_date;
		this.staff_id = staff_id;
		this.last_update = last_update;
	}


	public int getRental_id() {
		return rental_id;
	}


	public void setRental_id(int rental_id) {
		this.rental_id = rental_id;
	}


	public Timestamp getRental_date() {
		return rental_date;
	}


	public void setRental_date(Timestamp rental_date) {
		this.rental_date = rental_date;
	}


	public int getInventory_id() {
		return inventory_id;
	}


	public void setInventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}


	public int getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}


	public Timestamp getReturn_date() {
		return return_date;
	}


	public void setReturn_date(Timestamp return_date) {
		this.return_date = return_date;
	}


	public int getStaff_id() {
		return staff_id;
	}


	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}


	public Timestamp getLast_update() {
		return last_update;
	}


	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	
	
	
}
