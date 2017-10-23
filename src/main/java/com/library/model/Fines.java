package com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fines {
	@Id
	private int loan_id;
	@Column(columnDefinition="Decimal(10,2) default '100.00'")
	private double fine_amt;
	@Column(columnDefinition="int default 0")
	private int paid;
	private Boolean action;
	public Boolean getAction() {
		return action;
	}
	public void setAction(Boolean action) {
		this.action = action;
	}
	public Fines(){
		this.fine_amt=0.0;
		this.paid='N';
	}
	

	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public double getFine_amt() {
		return fine_amt;
	}
	public void setFine_amt(double fine_amt) {
		this.fine_amt = fine_amt;
	}
	public int getPaid() {
		return paid;
	}
	public void setPaid(int paid) {
		this.paid = paid;
	}

	
	
}
