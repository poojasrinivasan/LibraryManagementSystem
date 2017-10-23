package com.library.service;
import java.util.List;

import com.library.model.BookDetails;

public interface BookService {
 
	public List<BookDetails> getAllBooks();
	public void addBook(BookDetails book);
	public void prepopulateData();
	public List<BookDetails> getBooklist(String value);

}
