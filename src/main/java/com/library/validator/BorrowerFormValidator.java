package com.library.validator;
import com.library.model.Borrower;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BorrowerFormValidator implements Validator{
	
	private Pattern pattern;  
	 private Matcher matcher;  
	 String MOBILE_PATTERN = "\\d{3}-\\d{3}-\\d{4}";  
	 String ID_PATTERN = "[0-9]+";  
	  
	 @Override
		public boolean supports(Class<?> clazz) {
			return Borrower.class.equals(clazz);
		}
	  
	 @Override  
	 public void validate(Object target, Errors errors) {  
	  
	  Borrower b = (Borrower) target;  
	  
	  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bname", "NotEmpty.borrower.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.borrower.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ssn", "NotEmpty.borrower.ssn");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone","NotEmpty.borrower.phone");
	  
	Integer ssn=b.getSsn();
		
	// input ssn conatains numeric values only  
	  if (ssn != null) {  
	   pattern = Pattern.compile(ID_PATTERN);  
	   matcher = pattern.matcher(ssn.toString());  
	   if (!matcher.matches()) {  
	    errors.rejectValue("ssn", "ssn.incorrect",  
	      "Enter a numeric value");  
	   }  
	   

	// input string can not exceed that a limit  
	   if (ssn.toString().length() != 9) {  
	    errors.rejectValue("ssn", "ssn.exceed",  
	      "SSN should contain only 9 digit");  
	   }  
	  }  
	  
	// phone number validation  
	  if (!(b.getPhone() != null && b.getPhone().isEmpty())) {  
	   pattern = Pattern.compile(MOBILE_PATTERN);  
	   matcher = pattern.matcher(b.getPhone());  
	   if (!matcher.matches()) {  
	    errors.rejectValue("phone", "phone.incorrect",  
	      "Enter a correct phone number-eg.999 585-4009");  
	   }  
	  }  
	  
	 
	 }  
}
