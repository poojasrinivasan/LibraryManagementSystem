package com.library.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.dao.FineDao;
import com.library.model.Fines;

@Service
@Transactional
public class FineServiceImpl implements FineService{
	@Autowired
	private FineDao fineDao;
	
   public void setFineDao(FineDao fineDao) {
		this.fineDao = fineDao;
	}
   
   @Override
   @Transactional
	public void updateFine(){
	   try{
			fineDao.updateFineForBooksNotReturned();
		    fineDao.updateFineForReturnedBooks();
	
		}
	   catch(Exception e){
		   System.out.println(e);
	   }
	}
 
   @Override
   @Transactional
   public double getTotalFine(int cardid){
	   double totalfine=fineDao.totalFine(cardid);
	   return totalfine;
   }
   @Override
   @Transactional
   public List<Fines> getFineForEachBook(int cardid){
	  List<Fines> bookfinelist= fineDao.getFineForEachBook(cardid);
	return bookfinelist;
	   
   }
   @Override
   @Transactional
   public int payFine(int loanid){
	   return fineDao.payFine(loanid);
   }
}

