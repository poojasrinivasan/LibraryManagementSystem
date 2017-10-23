package com.library.dao;
import com.library.model.BookDetails;
import java.util.List;

public interface Bookdao {
	public void addBook(BookDetails book);
    public List<BookDetails> getAllBooks();
    public void createBookTable();
    public void createAuthorTable();
    public void createBookAuthorTable();
	public List<BookDetails> getBooklist(String value);
	 public void updateBookAvailability(String isbn,Character availability);
	 public Character getBookAvailability(String isbn);
	  public BookDetails getBooks(String isbn);
	 
}
