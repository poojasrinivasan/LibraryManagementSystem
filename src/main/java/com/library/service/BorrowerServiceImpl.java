package com.library.service;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.dao.BorrowerDao;
import com.library.model.Borrower;
import com.library.model.PreProcessing;

@Service
@Transactional
public class BorrowerServiceImpl implements BorrowerService{
	@Autowired
	private BorrowerDao borrowerDao;
	@Override
	@Transactional
	 public void prepopulateBorrower(){
		List<Borrower> availableborrowerList=borrowerDao.getBorrowerData();
		if(availableborrowerList.size()==0){
		List<Borrower> borrowerList=new LinkedList<Borrower>();
		int ssn=100000000;
			try{
				File excelFile = new File("C:/Users/Pooja/Desktop/Database/borrowers.xlsx"); // Change the location and file name as per yours
				PreProcessing uploaded = new PreProcessing(excelFile);
				ArrayList<ArrayList<Object>> list = uploaded.extractAsList(); // Rows in excel will be returned as list
				
				for(ArrayList<Object> singleRow : list){
				//	int id=Integer.parseInt(String.valueOf(singleRow.get(0)));
					String names[]=new String[2];
					names[0]=String.valueOf(singleRow.get(1));
					names[1]=String.valueOf(singleRow.get(2));
					String fullname="";
					for(String s:names){
						fullname+=s;
					}
					//String email=String.valueOf(singleRow.get(3));
					String streetaddress=String.valueOf(singleRow.get(4));
					String city=String.valueOf(singleRow.get(5));
					String state=String.valueOf(singleRow.get(6));
					String address="";
					address=streetaddress+city+state;
					String phone=String.valueOf(singleRow.get(7));
					Borrower b=new Borrower();
				//	b.setCard_id(id);
					b.setBname(fullname);
					b.setAddress(address);
					b.setPhone(phone);
					b.setSsn(ssn);
					borrowerList.add(b);
					ssn++;
				}
				for(Borrower borrower:borrowerList){
					borrowerDao.addBorrower(borrower);
				}
				
			}
			catch(Exception E){
				System.out.println(E);
			}
			}
			
				}
		
	 
	@Override
	@Transactional
	  public List<Borrower> getBorrowerData(){
		  return borrowerDao.getBorrowerData();
	  }
	@Override
	@Transactional
	  public Borrower getBorrowerDetail(int cardid){
		  List<Borrower> borrowerlist= borrowerDao.getBorrowerDetail(cardid);
		  Borrower borrower=borrowerlist.get(0);
		  return borrower;
		  
	  }
	@Override
	@Transactional
	  public int addBorrower(Borrower borrower) throws Exception{
		List<Borrower> borrowerList=getBorrower(borrower.getSsn());
		if(borrowerList.size()==0){
			 borrowerDao.addBorrower(borrower);
			 List<Borrower> updatedborrowerList=getBorrower(borrower.getSsn());
			 Borrower borrower1=updatedborrowerList.get(0);
			 return borrower1.getCard_id();
		}
		else{
			throw new Exception("Borrower ssn is already registered");
			
		}
	  }
	
	@Override
	@Transactional
	  public List<Borrower> getBorrower(int ssn){
		List<Borrower> borrowerList= borrowerDao.getBorrower(ssn);
	    return borrowerList;
	  }
	public void setBorrowerDao(BorrowerDao borrowerdao){
		this.borrowerDao=borrowerdao;
	}
}
