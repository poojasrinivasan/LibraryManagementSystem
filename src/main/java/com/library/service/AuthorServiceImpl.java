package com.library.service;
import com.library.dao.AuthorDao;
import com.library.model.Author;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorDao authorDao;

	@Override
	@Transactional
	public void addAuthor(Author author){
		authorDao.addAuthor(author);
	}
	@Override
	@Transactional
    public List<Author> getAuthorList(){
    	List<Author> authorlist=authorDao.getAuthorList();
    	return authorlist;
    }
	public void setAuthorDao(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}
}
