package com.library.dao;

import java.util.List;

import com.library.model.Book_Author;

public interface BookAuthorDao {
	
		public void addAuthor(Book_Author author);
		public List<Book_Author> getAuthorList();
	

}
