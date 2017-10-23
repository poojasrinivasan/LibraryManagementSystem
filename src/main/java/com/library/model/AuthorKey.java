package com.library.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AuthorKey implements Serializable {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AuthorKey(String authorid,String isbn){
		this.authorid=authorid;
		this.isbn=isbn;
	}
	 public String getAuthorid() {
		return authorid;
	}
	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	@Column(name="authorid")
		private String authorid;
	 @Column(name="isbn")
	   private String isbn;
	}


