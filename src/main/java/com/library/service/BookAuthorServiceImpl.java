package com.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.dao.BookAuthorDao;
import com.library.model.Book_Author;

@Service
@Transactional
public class BookAuthorServiceImpl implements BookAuthorService {
	@Autowired
	private BookAuthorDao bookauthordao;
	
	@Override
	@Transactional
	public void addAuthor(Book_Author author){
		bookauthordao.addAuthor(author);
	}
	@Override
	@Transactional
	public List<Book_Author> getAuthorList(){
		return bookauthordao.getAuthorList();
	}
	public void setBookauthordao(BookAuthorDao bookauthordao) {
		this.bookauthordao = bookauthordao;
	}
}
