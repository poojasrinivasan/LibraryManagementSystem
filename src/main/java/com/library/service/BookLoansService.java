package com.library.service;

import java.util.List;

import com.library.model.BookDetails;
import com.library.model.Book_Loans;

public interface BookLoansService {
public void addBookBorrower(String isbn,int cardid) throws Exception;
public int getBookCountByBorrower(int cardid);
public BookDetails getBooks(String isbn);
public List<Book_Loans> getBookLoanDetails(String searchText);
public int checkinBooks(List<Integer> loanids);

}
