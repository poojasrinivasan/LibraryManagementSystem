package com.library.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.dao.BookLoansDao;
import com.library.dao.Bookdao;
import com.library.dao.BorrowerDao;
import com.library.model.BookDetails;
import com.library.model.Book_Loans;
import com.library.model.Borrower;

@Service
@Transactional
public class BookLoansServiceImpl implements BookLoansService {
	
	@Autowired
	private BookLoansDao bookLoansDao;
	
	@Autowired
	private BorrowerDao borrowerDao;
	
	@Autowired
	private Bookdao bookdao;
	
	public void setBookdao(Bookdao bookdao) {
		this.bookdao = bookdao;
	}


	public void setBookLoansDao(BookLoansDao bookLoansDao) {
		this.bookLoansDao = bookLoansDao;
	}


	public void setBorrowerDao(BorrowerDao borrowerDao) {
		this.borrowerDao = borrowerDao;
	}

	@Override
	@Transactional
	public void addBookBorrower(String isbn,int cardid) throws Exception{
		System.out.println(cardid);
		Book_Loans loanentry=new Book_Loans();
		List<Borrower> borrowerentry=borrowerDao.getBorrowerDetail(cardid);
		
        Character availability=bookdao.getBookAvailability(isbn);
        if(availability=='N'){
        	throw new Exception("Book is already checked out");
        }
        loanentry.setIsbn(isbn);	
		if(borrowerentry.size()==1){
			loanentry.setCard_id(cardid);
			int borrowerLoanCount=getBookCountByBorrower(cardid);
			if(borrowerLoanCount==3){
				throw new Exception("Borrower has a maximum of 3 book loans already");
			}
		}
		else{
			throw new Exception("Card id invalid.Create a new Borrower entry");
		}
		Date todaysdate=new java.util.Date();
		 // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(todaysdate);
   //     java.sql.Date ourJavaDateObject=new java.sql.Date()
	    loanentry.setDate_out(todaysdate);
	    c.add(Calendar.DATE, 14);
	    //convert calendar to date
        Date due_date = c.getTime();
	  	loanentry.setDue_date(due_date);
	 	bookLoansDao.addBookBorrower(loanentry);
		Character available='N';
		bookdao.updateBookAvailability(isbn,available);
		
	
		
	}
	
	@Override
	@Transactional
	public int getBookCountByBorrower(int cardid){
		return bookLoansDao.getBookCountByBorrower(cardid);
	}
	@Override
	@Transactional
	public BookDetails getBooks(String isbn){
		return bookdao.getBooks(isbn);
	}
	
	@Override
	@Transactional
	public List<Book_Loans> getBookLoanDetails(String searchText){
		List<Book_Loans> bookloanlist= bookLoansDao.getBookLoanDetails(searchText);
		      return bookloanlist;
          }
	
	@Override
	@Transactional
	public int checkinBooks(List<Integer> loanids){
		int count=0;
		try{
		for(Integer loanid:loanids){
	
			bookLoansDao.checkinBooks(loanid);
		    String isbn=bookLoansDao.getBookIsbn(loanid);
		    Character availability='Y';
		    bookdao.updateBookAvailability(isbn, availability);
			count++;
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		return count;
	}
}