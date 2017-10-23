package com.library.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.library.model.Borrower;

@Repository
public class BorrowerDaoImpl implements BorrowerDao {
	 @Autowired
	 private SessionFactory sessionFactory;
	 
	 @SuppressWarnings("unchecked")
	 public List<Borrower> getBorrowerData(){
   	  return sessionFactory.getCurrentSession().createQuery("from Borrower").list();
	 }
	  public void addBorrower(Borrower data){
		  sessionFactory.getCurrentSession().save(data);
		 	  }
	  @SuppressWarnings("unchecked")
	public List<Borrower> getBorrowerDetail(int cardid){
		  String hql="from Borrower where card_id=:cardid";
	   	   Query query=sessionFactory.getCurrentSession().createQuery(hql);
	   	   query.setParameter("cardid",cardid);
		 return query.list();
		  
	  }
	  @SuppressWarnings("unchecked")
	public List<Borrower> getBorrower(int ssn){
		  String hql="from Borrower where ssn=:ssn";
		  Query query=sessionFactory.getCurrentSession().createQuery(hql);
	   	   query.setParameter("ssn",ssn);
		   return query.list();
		 
	  }
}
