package com.library.validator;
import com.library.model.Borrower;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BorrowerFormValidator2 implements Validator{
	
	private Pattern pattern;  
	 private Matcher matcher;  
	  
	
	 String MOBILE_PATTERN = "[0-9]{10}";  
	  
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
	  
	
	  
	// phone number validation  
	  if (!(b.getPhone() != null && b.getPhone().isEmpty())) {  
	   pattern = Pattern.compile(MOBILE_PATTERN);  
	   matcher = pattern.matcher(b.getPhone());  
	   if (!matcher.matches()) {  
	    errors.rejectValue("phone", "phone.incorrect",  
	      "Enter a correct phone number");  
	   }  
	  }  
	  
	 
	 }  
}
