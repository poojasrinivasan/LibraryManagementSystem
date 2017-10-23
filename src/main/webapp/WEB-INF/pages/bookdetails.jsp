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
      
      <!-- CSS -->
      <style>
         .ui-widget-header,.ui-state-default, ui-button {
            background:#b9cd6d;
            border: 1px solid #b9cd6d;
            color: #FFFFFF;
            font-weight: bold;
         }
      </style>
      
 



<script>
$(document).ready(function(){
  $("#checkout").click(function(){
	 var borrowerid = prompt("Enter the borrower id", "Enter here");
	
	    $("#borrowerid").val(borrowerid);
	    var str = $("#form").serialize();
	    $.ajax({
	    	  data:   str, 
	          url : 'checkout',
	          type: "POST",
	          cache: false,
	          success : function(response) {
	          alert(response);
	          url = window.location.href;
	            url = url.replace("search", "");
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
    <div class="container"  align="center">
        <h1>Book List</h1>
   
     <form:form id="form" modelAttribute="Booklist" action="checkout" method="post">
    <input type="button" id="checkout" value="checkout"/>
     
      <table border="1">
          
            <th>ISBN10</th>
            <th>TITLE</th>
            <th>Author</th>
            <th>Available</th>
            <th>checkout</th>
          
            <c:forEach items="${Booklist.bookdetailslist}" var="book" varStatus="status">
		<tr>
	          <td>${book.isbn10}</td>
                     <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.available}</td>
			<input type="hidden" name="bookdetailslist[${status.index}].isbn10" value="${book.isbn10}"/>
			<input type="hidden" name="bookdetailslist[${status.index}].title" value="${book.title}"/>
			<input type="hidden" name="bookdetailslist[${status.index}].author" value="${book.author}"/>
			<input type="hidden" name="bookdetailslist[${status.index}].available" value="${book.available}"/></td>
		  
		    
			<td><form:checkbox path="bookdetailslist[${status.index}].action" id="${book.isbn10}" value="${book.action}"/></td> 
			
		
			
		</tr>
	</c:forEach>
               <input type="hidden" name="borrowerid" id="borrowerid"/></td>
        
            </table> 
             </form:form>
           
      
        
    </div>
</body>
</html>