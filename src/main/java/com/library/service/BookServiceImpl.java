package com.library.service;
import com.library.dao.AuthorDao;
import com.library.dao.BookAuthorDao;
import com.library.dao.Bookdao;
import com.library.model.BookDetails;
import com.library.model.Book_Author;
import com.library.model.Author;
import com.library.model.AuthorKey;
import com.library.model.PreProcessing;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class BookServiceImpl implements BookService{
	
	@Autowired
	private Bookdao bookdao;
	
	@Autowired
	private AuthorDao authordao;
	
	@Autowired
	private BookAuthorDao bookAuthorDao;

	

	@Override
	@Transactional
	public void prepopulateData(){
		List<BookDetails> bookdetails=bookdao.getAllBooks();
		if(bookdetails.size()==0){
		List<BookDetails> books=new LinkedList<>();
		String authorid="A00";
		int index=0;
		
		try{
			File excelFile = new File("C:/Users/Pooja/Desktop/booksdata1.xlsx"); // Change the location and file name as per yours
			PreProcessing uploaded = new PreProcessing(excelFile);
			ArrayList<ArrayList<Object>> list = uploaded.extractAsList(); // Rows in excel will be returned as list
			HashMap<String,String> authors = new HashMap<String,String>(); 
			Set<String> authorset=new HashSet<String>();
			for(ArrayList<Object> singleRow : list){
		          String isbn10 = String.valueOf(singleRow.get(0)) ;
		          //String isbn13 = String.valueOf(singleRow.get(1)) ;
		          String title = String.valueOf(singleRow.get(2)) ;
		    	  String authorname=String.valueOf(singleRow.get(3));
		               
		           BookDetails b=new BookDetails();
				          b.setIsbn10(isbn10);
				         // b.setIsbn13(isbn13);
				          b.setTitle(title);
				          b.setAuthor(authorname);
				          b.setAvailable('Y');
				          books.add(b);
				   String[] authorslist=authorname.split(",") ;
				   Set<String> uniqueAuthornames=new HashSet<>();
				   for(String author:authorslist){
				   	uniqueAuthornames.add(author);
			    	}
				   
				       for(String author:uniqueAuthornames){
				    	   String id="";
				    	   id=authors.get(author);
				    	
				     if(authorset.contains(author)==false){
				       	authorset.add(author);
				          if(authors.containsKey(author)!=false){
			        		  id=authors.get(author);
			        		  
			        		  		        	  }
			        	  else{
			        		 index++;
			        		 id=authorid+index;
			        		authors.put(author, id);
			        		
			        	  }
				          Author obj=new Author();
			        	  obj.setAuthor_id(id);
			        	  obj.setName(author);
			        	  authordao.addAuthor(obj);
		          }
				        
				       AuthorKey key=new AuthorKey(id, isbn10);
				       Book_Author ba=new Book_Author(key);
				       bookAuthorDao.addAuthor(ba);
				       }
				     
					         
				          
			}
			
			
        	  
		     
		         
		      		  			       	        		}
		catch(Exception E){
			System.out.println(E);
		}
		
		for (BookDetails eachbook : books){
			bookdao.addBook(eachbook);
					}
		
        bookdao.createBookTable();
       // bookdao.createAuthorTable();
       // bookdao.createBookAuthorTable();
		}
		
	
	
	}
	
	@Override
	@Transactional
	public List<BookDetails> getAllBooks(){
						
		return bookdao.getAllBooks();
		
		
		
	}
	
	@Override
	@Transactional
	public void addBook(BookDetails book){
					bookdao.addBook(book);
		}
	@Override
	@Transactional
	public List<BookDetails> getBooklist(String value){
		return bookdao.getBooklist(value);
		
	}
	public void setBookdao(Bookdao bookdao){
		this.bookdao=bookdao;
	} 
	public void setAuthorDao(AuthorDao authordao){
		this.authordao=authordao;
	}
	public void setBookAuthorDao(BookAuthorDao bookAuthorDao) {
		this.bookAuthorDao = bookAuthorDao;
	}
}
