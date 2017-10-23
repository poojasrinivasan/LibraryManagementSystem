<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Borrower</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
</head>

<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
     <ul class="nav navbar-nav">
      <li class="active"><a href="">Home</a></li>
   
    </ul>
  </div>
</nav>
<br/>
<br/>

<div class="container" align="center">
<h2>Add New Borrower</h2>
<br/><br/>
<form:form method="POST" commandName="borrower"
		action="saveBorrower" class="form-horizontal">
		
		<spring:bind path="bname">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-10">
					<form:input path="bname" type="text" class="form-control" id="bname" placeholder="Name" />
					<form:errors path="bname" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="ssn">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">SSN</label>
				<div class="col-sm-10">
					<form:input path="ssn" type="number" class="form-control" id="ssn" placeholder="SSN" />
					<form:errors path="ssn" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="address">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Address</label>
				<div class="col-sm-10">
					<form:input path="address" type="text" class="form-control" id="address" placeholder="Address" />
					<form:errors path="address" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
			<spring:bind path="phone">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Phone</label>
				<div class="col-sm-10">
					<form:input path="phone" type="number" class="form-control" id="phone" placeholder="Phone" />
					<form:errors path="phone" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				
						<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					
			</div>
		</div>
		
	

	</form:form>
</div>
</body>
</html>