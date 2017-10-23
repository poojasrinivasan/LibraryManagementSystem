package com.library.dao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import com.library.model.BookDetails;
@Repository
public class BookdaoImpl implements Bookdao
{
	 @Autowired
	    private SessionFactory sessionFactory;
	 
      public void addBook(BookDetails book){
	    sessionFactory.getCurrentSession().saveOrUpdate(book);
       }
      @SuppressWarnings("unchecked")
      public List<BookDetails> getAllBooks(){
    	  return sessionFactory.getCurrentSession().createQuery("from BookDetails").list();
      }
     
      public void createBookTable(){
    	  String hql="insert into Book(isbn,title) Select isbn10,title from BookDetails group by isbn10";
    	   Query query=sessionFactory.getCurrentSession().createQuery(hql);
    	   query.executeUpdate();
    	   
      }
      public void createAuthorTable(){
   	  String hql="insert into Author(author_id,name) Select authorid,author from BookDetails group by authorid";
   	   Query query=sessionFactory.getCurrentSession().createQuery(hql);
	   query.executeUpdate();
      }
      public void createBookAuthorTable(){
    	  String hql="insert into Book_Author(authorid,isbn) Select a.authorid,b.isbn10 from BookDetails b,Author a where a.name=b.name and a.name=:";
    	  Query query=sessionFactory.getCurrentSession().createQuery(hql);
    	 
      	query.executeUpdate();
        }
      @SuppressWarnings("unchecked")
      public List<BookDetails> getBooklist(String value){
    	 /* String hql="from BookDetails where title LIKE CONCAT('%', :titlename, '%') 
    	  * or author LIKE CONCAT('%', :authorname ,'%')";
    	  Query query = sessionFactory.getCurrentSession().createQuery(hql);
    	  query.setParameter("titlename",value);
    	  query.setParameter("authorname",value);
    	  List<BookDetails> results = query.list();
    	  return results;*/
    	  String[] searchKeys=value.split(" ");
  	  	
     	 String hql1="Select * from BookDetails where";
     	 for(int i=0;i<searchKeys.length;i++){
     		 if(i!=0){
     			  hql1+="or";
     		 }
     		
     		 hql1 += " isbn10 LIKE '%"+searchKeys[i]+"%'"
  	    	 		+ "or title LIKE '%"+searchKeys[i]+"%'"
  	    	 		+ " or author LIKE '%"+searchKeys[i]+"%'";
  	    	
     	 }
     	  SQLQuery query= sessionFactory.getCurrentSession().createSQLQuery(hql1);
     	  List<Object[]> results = query.list();
     	  List<BookDetails> bookList=new ArrayList<>();
     	  for(Object obj:results){
     		 Object[] fields = (Object[]) obj;
     		 String isbn=String.valueOf(fields[0]);
     		String title=String.valueOf(fields[5]);
     		String author=String.valueOf(fields[2]);
     		char available = fields[3].toString().charAt(0);
     		BookDetails b=new BookDetails();
     		b.setIsbn10(isbn);
     		b.setAuthor(author);
     		b.setTitle(title);
     		b.setAvailable(available);
     		 bookList.add(b);
     	  }
   	      return bookList;
     	  
  	}
      
      public void updateBookAvailability(String isbn,Character availability){
    	  String hql="update BookDetails set available=:a where isbn10=:isbn";
       	  Query query = sessionFactory.getCurrentSession().createQuery(hql);
    	  query.setParameter("isbn",isbn);
    	  query.setParameter("a",availability);
    	  query.executeUpdate();
    	  
      }
      @SuppressWarnings("unchecked")
      public Character getBookAvailability(String isbn){
    	  String hql="from BookDetails where isbn10=:isbn";
    	  Query query = sessionFactory.getCurrentSession().createQuery(hql);
    	  query.setParameter("isbn",isbn);
    	  List<BookDetails> results = query.list();
    	  BookDetails book=results.get(0);
    	  return book.getAvailable();
      }
      @SuppressWarnings("unchecked")
      public BookDetails getBooks(String isbn){
    	  String hql="from BookDetails where isbn10=:isbn";
    	  Query query = sessionFactory.getCurrentSession().createQuery(hql);
    	  query.setParameter("isbn",isbn);
    	  List<BookDetails> results = query.list();
    	  BookDetails book=results.get(0);
    	  return book;
      }
      
}
