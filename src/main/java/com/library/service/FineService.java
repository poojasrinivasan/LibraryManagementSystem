package com.library.service;

import java.util.List;

import com.library.model.Fines;

public interface FineService {
	public void updateFine();
	 public double getTotalFine(int cardid);
	 public List<Fines> getFineForEachBook(int cardid);
	 public int payFine(int loanid);
	
}
