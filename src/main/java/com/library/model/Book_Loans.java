package com.library.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book_Loans {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int loan_id;
@Column
private String isbn;
@Column
private int card_id;
@Column
private Date date_out;
@Column
private Date due_date;
@Column
private Date date_in;
private Boolean action;
public Boolean getAction() {
	return action;
}
public void setAction(Boolean action) {
	this.action = action;
}

public int getLoan_id() {
	return loan_id;
}
public void setLoan_id(int loan_id) {
	this.loan_id = loan_id;
}
public int getCard_id() {
	return card_id;
}
public void setCard_id(int card_id) {
	this.card_id = card_id;
}
public String getIsbn() {
	return isbn;
}
public void setIsbn(String isbn) {
	this.isbn = isbn;
}

public Date getDate_out() {
	return date_out;
}
public void setDate_out(Date date_out) {
	this.date_out = date_out;
}
public Date getDue_date() {
	return due_date;
}
public void setDue_date(Date due_date) {
	this.due_date = due_date;
}
public Date getDate_in() {
	return date_in;
}
public void setDate_in(Date date_in) {
	this.date_in = date_in;
}

}
