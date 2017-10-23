package com.library.service;
import com.library.model.Author;
import java.util.List;

public interface AuthorService {

	public void addAuthor(Author author);
	public List<Author> getAuthorList();
}
