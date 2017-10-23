package com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Authors")
public class Author{
@Id
private String author_id;
@Column
private String name;
public Author(){}
public Author(String author_id,String name){
	this.author_id=author_id;
	this.name=name;
}
public String getAuthor_id() {
	return author_id;
}
public void setAuthor_id(String author_id) {
	this.author_id = author_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
