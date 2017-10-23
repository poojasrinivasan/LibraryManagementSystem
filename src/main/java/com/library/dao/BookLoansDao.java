package com.library.dao;

import java.util.List;

import com.library.model.Book_Loans;

public interface BookLoansDao {

		public void addBookBorrower(Book_Loans bookloan);
		public int getBookCountByBorrower(int cardid);
		public List<Book_Loans> getBookLoanDetails(String searchText);
		public int checkinBooks(int loanid);
		 public String getBookIsbn(int loanid);
}
