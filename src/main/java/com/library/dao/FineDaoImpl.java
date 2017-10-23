package com.library.dao;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.library.model.Fines;

@Repository
public class FineDaoImpl implements FineDao {
	 @Autowired
	 private SessionFactory sessionFactory;
	
	public int updateFineForReturnedBooks(){
	 String hql="UPDATE Fines f INNER JOIN Book_Loans b ON f.loan_id=b.loan_id SET f.fine_amt=:fineamt*DATEDIFF(b.date_in,b.due_date)"
	 		+ "WHERE b.date_in IS NOT NULL and DATEDIFF(b.date_in,b.due_date)>:value";
	 SQLQuery  query=sessionFactory.getCurrentSession().createSQLQuery(hql);
	 query.setParameter("fineamt", 0.25);
	 query.setParameter("value", 0);
	 int result=query.executeUpdate();
	 return result;
     }
	
	public int updateFineForBooksNotReturned(){
		 insertIntoFine();
		 String hql="UPDATE Fines f INNER JOIN Book_Loans b ON f.loan_id=b.loan_id SET f.fine_amt=:fineamt*DATEDIFF(:todaysdate,b.due_date) WHERE b.date_in IS NULL";
		 SQLQuery  query=sessionFactory.getCurrentSession().createSQLQuery(hql);
		 Date todaysdate=new java.util.Date();
		 // convert date to calendar
	     Calendar c = Calendar.getInstance();
	     c.setTime(todaysdate);
	     query.setParameter("fineamt", 0.25);
	     query.setParameter("todaysdate",todaysdate);
		 int result=query.executeUpdate();
		 return result;
	     }
	
	
	public void insertIntoFine(){
		
		 String hql="INSERT INTO Fines(loan_id) SELECT b.loan_id from Book_Loans b"
		 		+ " WHERE b.date_in IS NULL and DATEDIFF(:todaysdate,b.due_date)>0 "
		 		+ "and b.loan_id NOT IN (SELECT loan_id from Fines)";
		 Query query=sessionFactory.getCurrentSession().createQuery(hql);
		 Date todaysdate=new java.util.Date();
		 // convert date to calendar
	     Calendar c = Calendar.getInstance();
	     c.setTime(todaysdate);
		 query.setParameter("todaysdate",todaysdate);
		 query.executeUpdate();
		 
		 String hql2="INSERT INTO Fines(loan_id) SELECT b.loan_id from Book_Loans b"
			 		+ " WHERE b.date_in IS NOT NULL and DATEDIFF(b.date_in,b.due_date)>0 "
			 		+ "and b.loan_id NOT IN (SELECT loan_id from Fines)";
	    Query query2=sessionFactory.getCurrentSession().createQuery(hql2);
		query2.executeUpdate();
		 
		 }
			

    public double totalFine(int cardid){
    	String hql="Select B.card_id,sum(f.fine_amt) from Fines F,Book_Loans B where F.loan_id=B.loan_id and F.paid=:paid and B.card_id=:cardid group by B.card_id";
    	 SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery(hql);
    	 query.setParameter("paid",0);
    	 query.setParameter("cardid",cardid);
    	 List result=query.list();
    	 double count=0.0;
    	 for (Object obj : result) {
    		    Object[] fields = (Object[]) obj;
    		    BigDecimal b=(BigDecimal)fields[1];
    		    count=b.doubleValue();
    		}
    	    	 return count;
    }
    
    @SuppressWarnings("unchecked")
    public List<Fines> getFineForEachBook(int cardid){
    	String hql="from Fines F where F.loan_id in (SELECT B.loan_id FROM Book_Loans B where B.card_id=:cardid)";
    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
    	 query.setParameter("cardid",cardid);
    	 List<Fines> result=query.list();
    	 return result;
    }
    
    public int payFine(int loanid){
    	String hql="Update Fines Set fine_amt=:fineamt,paid=:valuepaid where loan_id=:loanid and "
    			+ "loan_id in (SELECT loan_id from Book_Loans where date_in IS NOT NULL)";
    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
    	query.setParameter("fineamt", 0.0);
    	query.setParameter("valuepaid",1);
    	query.setParameter("loanid",loanid);
    	int result=query.executeUpdate();
    	return result;
    }
	
    
}
