package com.library.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="Book_Authors")
public class Book_Author{

	@EmbeddedId
	private AuthorKey authorkey;
  
	public Book_Author(AuthorKey authorKey){
		this.authorkey=authorKey;
	}
	public AuthorKey getAuthorkey() {
		return authorkey;
	}

	public void setAuthorkey(AuthorKey authorkey) {
		this.authorkey = authorkey;
	}
	
}
