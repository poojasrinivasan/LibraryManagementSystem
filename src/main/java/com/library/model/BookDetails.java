package com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;import javax.persistence.Table;

@Entity
@Table(name="BookDetails")
public class BookDetails {
@Id	
@Column(name = "isbn10",length=10,unique = true)
private String isbn10;
@Column
private String title;
@Column
private String author;
@Column
private Character available;
private int borrowerid;
private Boolean action;

public Boolean getAction() {
	return action;
}
public void setAction(Boolean action) {
	this.action = action;
}
public int getBorrowerid() {
	return borrowerid;
}
public void setBorrowerid(int borrowerid) {
	this.borrowerid = borrowerid;
}
public Character getAvailable() {
	return available;
}
public void setAvailable(Character available) {
	this.available = available;
}


public BookDetails(){
	
}
public BookDetails(String isbn10,String isbn13,String title,String author){
	this.setIsbn10(isbn10);
	this.title=title;
	this.author=author;
	
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}

public String getIsbn10() {
	return isbn10;
}
public void setIsbn10(String isbn10) {
	this.isbn10 = isbn10;
}

public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
}
