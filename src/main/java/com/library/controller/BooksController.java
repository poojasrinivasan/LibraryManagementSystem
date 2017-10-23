package com.library.controller;
import com.library.service.BookLoansService;
import com.library.service.BookService;
import com.library.service.BorrowerService;
import com.library.service.FineService;
import com.library.validator.BorrowerFormValidator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.library.model.BookDetails;
import com.library.model.BookForm;
import com.library.model.BookLoanForm;
import com.library.model.Book_Loans;
import com.library.model.Borrower;
import com.library.model.FineForm;
import com.library.model.Fines;

@Controller
public class BooksController {
	public BooksController() {
		System.out.println("BooksController()");
	}
	@Autowired
	private BookService bookservice;
	@Autowired
	private BorrowerService borrowerService;
	@Autowired
	private BookLoansService bookLoanService;
	@Autowired
	private FineService fineService;
	@Autowired
	@Qualifier("borrowerFormValidator")
	private Validator validator;

	
	 //Externalize this value
    @Value("${myApplication.autoGrowCollectionLimit:25000}")
    private int autoGrowCollectionLimit;

   
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setAutoGrowCollectionLimit(autoGrowCollectionLimit);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        
    }
    
    @InitBinder("borrower")
    protected void initBinder2(WebDataBinder binder) {
        binder.setValidator(validator);
    }
	
    @ModelAttribute("borrower")
	public Borrower createBorrowerModel() {
		// ModelAttribute value should be same as used in the borrowerForm.jsp
		return new Borrower();
	}
    
	@RequestMapping(value = "/")
	public ModelAndView listBooks(ModelAndView model) throws IOException {
		bookservice.prepopulateData();		
		borrowerService.prepopulateBorrower();
		model.setViewName("home");
		return model;
	}
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public ModelAndView searchBooks(@RequestParam("searchkey") String param1 ){
		ModelAndView model=new ModelAndView();
		List<BookDetails> books= bookservice.getBooklist(param1);
		BookForm bookForm=new BookForm();
		bookForm.setBookdetailslist(books);
		model.addObject("Booklist", bookForm);
		model.setViewName("bookdetails");
		return model;
	}
	
	@RequestMapping(value="/checkout",method = RequestMethod.POST)
	public @ResponseBody String checkoutBooks(@ModelAttribute("Booklist") BookForm bookform){
		String message="";
		
		List<BookDetails> books=bookform.getBookdetailslist();
		  int borrowerid=bookform.getBorrowerid();
		System.out.println(borrowerid);
		 try{
	    for(BookDetails eachentry:books){
	    	 
			String isbn10=eachentry.getIsbn10();
			  
		    if(eachentry.getAction()){
		        bookLoanService.addBookBorrower(isbn10,borrowerid);
		    }
		    message="Checkout sussessful";
				
		}
		 } 
	    
	    	  catch(Exception e){
	    		  
	    		  message=e.getMessage();
	    		
			  }
	
	    System.out.println(message);
	    return message;
	   
	}
    
	
	
	@RequestMapping(value = "/searchLoan",method = RequestMethod.POST)
	public ModelAndView searchLoanBooks(@RequestParam("searchBookLoan") String param1 ){
		List<Book_Loans> bookloans=bookLoanService.getBookLoanDetails(param1);
		ModelAndView model=new ModelAndView();
		BookLoanForm bookLoanForm=new BookLoanForm();
		bookLoanForm.setBookLoanList(bookloans);
		model.addObject("BookLoanList",bookLoanForm);
		model.setViewName("checkin");
		return model;
	}
	
	@RequestMapping(value = "/checkin",method = RequestMethod.POST)
	public @ResponseBody String checkinBooks(@ModelAttribute("BookLoanList") BookLoanForm bookloanform){
		List<Book_Loans> bookloanlist=bookloanform.getBookLoanList();
		String message="";
		List<Integer> loanids=new ArrayList<>();
		for(Book_Loans loan:bookloanlist){
			if(loan.getAction()){
				loanids.add(loan.getLoan_id());
							}
		}
		int rowsupdated=bookLoanService.checkinBooks(loanids);
		message="Checkin successful.Rows updated:"+rowsupdated;
		return message;
	}
	
	
	@RequestMapping(value = "/addBorrower",method=RequestMethod.GET)
	public ModelAndView addBorrower(ModelAndView model) throws IOException {
		Borrower borrower=new Borrower();
		model.addObject("borrower", borrower);
		model.setViewName("borrowerForm");
		return model;
	}
	
	@RequestMapping(value = "/saveBorrower")
	public String saveBorrower(@ModelAttribute("borrower") @Validated Borrower borrower,BindingResult bindingResult, Model model){
		String message="";
		if (bindingResult.hasErrors()) {
			return "borrowerForm";
		}
		try{
			if (borrower.getCard_id() == 0) { 
				int card_id=borrowerService.addBorrower(borrower);
				message="Borrower added successfully.Card id : "+card_id;
					}
		}
		catch(Exception e){
			message=e.getMessage();
		}
		model.addAttribute("message", message);
		model.addAttribute("borrower",borrower);
		return "borrowerSaveSuccess";
	}
	
	@RequestMapping(value = "/refreshFine")
	public @ResponseBody String updateFine() {
		String result = "Fine details updated successfully";
		fineService.updateFine();
        return result;
    }
	
	@RequestMapping(value = "/displayFine",method=RequestMethod.POST)
	public ModelAndView displayFine(@RequestParam String cardid) {
		ModelAndView model=new ModelAndView();
		int card_id=Integer.parseInt(cardid);
		double totalfine=fineService.getTotalFine(card_id);		
		List<Fines> bookFineList=fineService.getFineForEachBook(card_id);
		FineForm fineform=new FineForm();
		fineform.setBookFineList(bookFineList);
		model.addObject("cardid",cardid);
		model.addObject("BookFineform",fineform);
		model.addObject("totalfine", totalfine);
		
		model.setViewName("finedetails");
        return model;
    }
	
	
	@RequestMapping(value = "/payFine",method=RequestMethod.POST)
	public @ResponseBody String payFine(@ModelAttribute("BookFineform") FineForm fineForm) {
		
		String message="";
		int count=0,result=0;
		List<Fines> loanlist=fineForm.getBookFineList();
		for(Fines f:loanlist){
			if(f.getAction()){
				count++;
			result=fineService.payFine(f.getLoan_id());
			
			}
		}
		int diff=count-result;
		if(diff==0){
			message="Payment of fine successful";
		}
		else{
			message="Payment for one or more books failed."
					+ "Reason:Fine cannot be settled for Books that is not returned yet.Please try again";
		}
		
	
	    return message;
    }
}
