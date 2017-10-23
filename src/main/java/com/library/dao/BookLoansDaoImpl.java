package com.library.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.library.model.Book_Loans;


@Repository
public class BookLoansDaoImpl implements BookLoansDao{
	@Autowired
    private SessionFactory sessionFactory;
 
	public void addBookBorrower(Book_Loans bookloan){
		 sessionFactory.getCurrentSession().saveOrUpdate(bookloan);
		
	}
	
	public int getBookCountByBorrower(int cardid){
	String hql="from Book_Loans where card_id=:cardid and date_in is NULL";
   	  Query query = sessionFactory.getCurrentSession().createQuery(hql);
   	  query.setParameter("cardid",cardid);
   	
   	  List results = query.list();
   	  return results.size();
		
	}
	 @SuppressWarnings("unchecked")
	public List<Book_Loans> getBookLoanDetails(String searchText){
		String hql="from Book_Loans b where b.loan_id in (select b1.loan_id from Book_Loans b1,Borrower b2 where b1.card_id=b2.card_id and (b1.card_id=:cardid or b1.isbn=:searchtext or b2.bname LIKE CONCAT('%', :searchtext, '%')))";
    	  Query query = sessionFactory.getCurrentSession().createQuery(hql);
    	  try{
    		 int cardid= Integer.parseInt(searchText);
    		 query.setParameter("cardid",cardid);
    	  }
    	  catch(Exception e){
    		  int cardid=-99999999;
    		  query.setParameter("cardid",cardid);
    	  }
       	 
    	  query.setParameter("searchtext",searchText);
    	  List<Book_Loans> results = query.list();
    
    	  return results;
	}
	
	 
	public int checkinBooks(int loanid){
		 String hql="update Book_Loans set date_in=:datein where loan_id=:loanid";
		 Query query = sessionFactory.getCurrentSession().createQuery(hql);
		 Date todaysdate=new java.util.Date();
		 // convert date to calendar
	     Calendar c = Calendar.getInstance();
	     c.setTime(todaysdate);
		 query.setParameter("datein", todaysdate);
		 query.setParameter("loanid", loanid);
		 int result=query.executeUpdate();
	     return result;
	}
	
	 @SuppressWarnings("unchecked")
	public String getBookIsbn(int loanid){
		 String hql="from Book_Loans where loan_id=:loanid";
		 Query query = sessionFactory.getCurrentSession().createQuery(hql);
		 query.setParameter("loanid", loanid);
		List<Book_Loans> obj=query.list();
		Book_Loans bookloan=obj.get(0);
		String isbn=bookloan.getIsbn();
	     return isbn;
	}
}
