package com.library.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.model.Author;
@Repository
public class AuthorDaoImpl implements AuthorDao{
	@Autowired
	 private SessionFactory sessionFactory;
     public void addAuthor(Author author){
		sessionFactory.getCurrentSession().saveOrUpdate(author);
	}
     @SuppressWarnings("unchecked")
    public List<Author> getAuthorList(){
    	return sessionFactory.getCurrentSession().createQuery("from Author").list();
    	
    }
}
