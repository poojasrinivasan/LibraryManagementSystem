package com.library.dao;

import java.util.List;

import com.library.model.Author;

public interface AuthorDao {
	public void addAuthor(Author author);
	public List<Author> getAuthorList();
}
