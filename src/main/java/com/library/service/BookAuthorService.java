package com.library.service;

import java.util.List;

import com.library.model.Book_Author;

public interface BookAuthorService {
	public void addAuthor(Book_Author author);
	public List<Book_Author> getAuthorList();
}
