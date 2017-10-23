package com.library.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.model.Book_Author;

@Repository
public class BookAuthorDaoImpl implements BookAuthorDao{
	@Autowired
	 private SessionFactory sessionFactory;

	public void addAuthor(Book_Author author){
		sessionFactory.getCurrentSession().saveOrUpdate(author);
	}
	  @SuppressWarnings("unchecked")
	public List<Book_Author> getAuthorList(){
		return sessionFactory.getCurrentSession().createQuery("from Book_Author").list();
    	
	}
}
