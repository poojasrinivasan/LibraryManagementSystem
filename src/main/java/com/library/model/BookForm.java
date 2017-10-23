package com.library.model;

import java.util.List;

public class BookForm {
private List<BookDetails> bookdetailslist;
private int borrowerid;

public int getBorrowerid() {
	return borrowerid;
}

public void setBorrowerid(int borrowerid) {
	this.borrowerid = borrowerid;
}

public List<BookDetails> getBookdetailslist() {
	return bookdetailslist;
}

public void setBookdetailslist(List<BookDetails> bookdetailslist) {
	this.bookdetailslist = bookdetailslist;
}
}
