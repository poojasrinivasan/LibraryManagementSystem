package com.library.service;
import com.library.model.Borrower;
import java.util.List;

public interface BorrowerService {
  public void prepopulateBorrower();
  public List<Borrower> getBorrowerData();
  public int addBorrower(Borrower data) throws Exception;
  public Borrower getBorrowerDetail(int cardid);
  public List<Borrower> getBorrower(int ssn);
}
