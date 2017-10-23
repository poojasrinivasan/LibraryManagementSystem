<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Checkin Book</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
         rel = "stylesheet">
<link href ="/LibraryManagementSystem/src/main/resources/css/usec.css"  rel = "stylesheet">
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
$(function() {
  
   $( "#checkin" ).click(function() {
	   
	   var str = $("#form2").serialize();
	    $.ajax({
	    	  data:   str, 
	          url : 'checkin',
	          type: "POST",
	          cache: false,
	          success : function(response) {
	          alert(response);
	          url = window.location.href;
	            url = url.replace("searchLoan", "");
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
   
    </ul>
  </div>
</nav>
    <div align="center" class="container">
        <h1>Checkin Books</h1>
         <div id="dialog-form" class="dialog">
          <form:form id="form2" modelAttribute="BookLoanList" action="checkin" method="post">
            <input type="button" id="checkin" value="checkin"/>
     <table border="1">
          
            <th>Loan_id</th>
            <th>card_id</th>
            <th>Date_out</th>
            <th>Due_Date</th>
            <th>Date_in</th>
          
            <c:forEach items="${BookLoanList.bookLoanList}" var="book" varStatus="status">
		<tr>
	          <td>${book.loan_id}</td>
                 <td>${book.card_id}</td>
                    <td>${book.date_out}</td>
                    <td>${book.due_date}</td>
                     <td>${book.date_in}</td>
			<input type="hidden" name="bookLoanList[${status.index}].loan_id" value="${book.loan_id}"/>
			<input type="hidden" name="bookLoanList[${status.index}].card_id" value="${book.card_id}"/>	
			<input type="hidden" name="bookLoanList[${status.index}].date_out" value="${book.date_out}"/>
			<input type="hidden" name="bookLoanList[${status.index}].due_date" value="${book.due_date}"/>
			<input type="hidden" name="bookLoanList[${status.index}].date_in" value="${book.date_in}"/>
		  	
		  	<td><form:checkbox path="bookLoanList[${status.index}].action" id="${book.loan_id}" value="${book.action}"/></td> 
			
		
			
		</tr>
	</c:forEach>
             
        
            </table> 
             </form:form>
           </div>
           
  </div>
</body>
</html>