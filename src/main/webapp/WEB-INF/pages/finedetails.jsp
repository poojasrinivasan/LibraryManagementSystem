<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library 2 Management Screen</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
         rel = "stylesheet">
      <script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
      <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
   <script>
$(function() {
  
   $("#pay").click(function() {
	   
	   var str = $("#form3").serialize();
	    $.ajax({
	    	  data:   str, 
	          url : 'payFine',
	          type: "POST",
	          cache: false,
	          success : function(response) {
	          alert(response);
	          url = window.location.href;
	            url = url.replace("displayFine", "");
	           window.location.replace(url);
	                  },
	          error : function(error){
	        	      alert('Error while request..');
	          }
	      });
  
	  
   });
});
</script>        
           
           
           </head>
<body>


<nav class="navbar navbar-default">
  <div class="container-fluid">
     <ul class="nav navbar-nav">
      <li class="active"><a href="">Home</a></li>
      <li><a href="addBorrower">New Borrower</a></li>
      <li><a  onclick="updateFine()">Update Fine</a></li>
       <li><a  onclick="payFine()">Pay Fine</a></li>
        <li><a  onclick="checkin()">Checkin Books</a></li>
    </ul>
  </div>
</nav>
<div class="container">
  <h3>Fine Details</h3>
  Card_id:${cardid}
  Total fine due:${totalfine}
  <br/>
    <form:form id="form3" modelAttribute="BookFineform" action="payFine" method="post">

       
     <table border="2">
          
            <th>Loan_id</th>
            <th>Fine_Amt</th>
            <th>Paid</th>
          
          
            <c:forEach items="${BookFineform.bookFineList}" var="book" varStatus="status">
		<tr>
	              <td>${book.loan_id}</td>
                   <td>${book.fine_amt}</td>
                   <td>${book.paid}</td>
                  
			<input type="hidden" name="bookFineList[${status.index}].loan_id" value="${book.loan_id}"/>
			<input type="hidden" name="bookFineList[${status.index}].fine_amt" value="${book.fine_amt}"/>
			<input type="hidden" name="bookFineList[${status.index}].paid" value="${book.paid}"/>
			
		  
		    
			<td><form:checkbox path="bookFineList[${status.index}].action" id="${book.loan_id}" value="${book.action}"/></td> 
			
		
			
		</tr>
	     </c:forEach>
               <input type="hidden" name="borrowerid" id="borrowerid" value="${cardid}"/></td>
        
            </table> 
            
               <br/><br/> 
            
          <input type="button" class="btn btn-outline-primary" name="pay" id="pay" value="PayFine"></input>
            
             </form:form>
         
              
  </div>

<div id="finedisplay">
</div>
</body>
</html>