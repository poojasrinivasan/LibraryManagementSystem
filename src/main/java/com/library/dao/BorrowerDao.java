package com.library.dao;

import java.util.List;
import com.library.model.Borrower;

public interface BorrowerDao {
	  public List<Borrower> getBorrowerData();
	  public void addBorrower(Borrower data);
	  public List<Borrower> getBorrowerDetail(int cardid);
	  public List<Borrower> getBorrower(int ssn);
}
