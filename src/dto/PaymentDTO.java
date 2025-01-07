package dto;

import java.sql.Timestamp;

public class PaymentDTO {
	
	protected int payment_id;
	protected int customer_id;
	protected int staff_id;
	protected int rental_id;
	protected double amount;
	protected Timestamp payment_date;
    protected Timestamp last_update;
    
    
	public PaymentDTO(int payment_id, int customer_id, int staff_id, int rental_id, double amount, Timestamp payment_date,
			Timestamp last_update) {
		super();
		this.payment_id = payment_id;
		this.customer_id = customer_id;
		this.staff_id = staff_id;
		this.rental_id = rental_id;
		this.amount = amount;
		this.payment_date = payment_date;
		this.last_update = last_update;
	}


	public int getPayment_id() {
		return payment_id;
	}


	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}


	public int getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}


	public int getStaff_id() {
		return staff_id;
	}


	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}


	public int getRental_id() {
		return rental_id;
	}


	public void setRental_id(int rental_id) {
		this.rental_id = rental_id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Timestamp getPayment_date() {
		return payment_date;
	}


	public void setPayment_date(Timestamp payment_date) {
		this.payment_date = payment_date;
	}


	public Timestamp getLast_update() {
		return last_update;
	}


	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
    
    
	
}
