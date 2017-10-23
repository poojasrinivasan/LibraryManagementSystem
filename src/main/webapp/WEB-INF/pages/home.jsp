<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<html lang="en">
<head>
  <title>Library Management System</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
         rel = "stylesheet">
      <script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
      <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
   <script >
   function updateFine(){
		$.ajax({
          url : 'refreshFine',
          type: "GET",
          cache: false,
          success : function(response) {
          alert(response);
                  },
          error : function(error){
        	      alert('Error while request..');
          }
      });
	   }
   
   $(function() {
	   $( "#dialog-form" ).dialog({
     	  autoOpen: false,
           modal: true,
           buttons: {
               "Ok": function() {
            	   $("#form3").submit();
               },
               "Cancel": function() {
             	  $(this).dialog("close");
               }
           }
     });
      
   });
   function payFine(){
	   $("#dialog-form").dialog("open");
		   }
  </script>
  <script>
  
      $(function() {
         $( "#dialog-form2" ).dialog({
         	  autoOpen: false,
               modal: true,
               buttons: {
                   "Ok": function() {
                       var text1 = $("#searchBookLoan");
                       $("#form1").submit();
                    
                     
                   },
                   "Cancel": function() {
                 	  $(this).dialog("close");
                   }
               }
         });
      
      });
      
      function checkin(){
      	 $("#dialog-form2").dialog("open");
      }
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
 <div id="dialog-form" style="display:none;">
    <form:form id="form3" action="displayFine" method="post">
    <label for="cardid">Enter the borrower card id</label>
         <input type="text" name="cardid" id="cardid" class="text ui-widget-content ui-corner-all" />
    </form:form>
 </div>
 
  
    <div id="dialog-form2" style="display: none;">
    <form:form id="form1" action="searchLoan" method="post">
    <label for="searchBookLoan">Enter the borrower card id,name or book isbn</label>
         <input type="text" name="searchBookLoan" id="searchBookLoan" class="text ui-widget-content ui-corner-all" />
  
  </form:form>
    </div>
<div class="container">
  <h3>Library Management System</h3>
  <p>This is a web application that allows librarian to search for books,help users to check-out or checkin books and update their fine details.New Borrowers can also be add to the system.</p>
  <form class="form-inline my-2 my-lg-0" action="search" method="post">
          <input class="form-control mr-sm-2" type="text" placeholder="Search" name="searchkey">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
</div>
<input type="hidden" name="borrowerid" id="borrowerid"/></td>
<div id="finedisplay">
</div>
</body>
</html>