package com.library.dao;

import java.util.List;

import com.library.model.Fines;

public interface FineDao {
	public int updateFineForReturnedBooks();
	public int updateFineForBooksNotReturned();
	  public double totalFine(int cardid);
	  public List<Fines> getFineForEachBook(int cardid);
	  public int payFine(int loanid);
	
}
