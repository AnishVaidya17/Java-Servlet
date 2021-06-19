<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	
	<h3>Enter Data for Employee Database</h3>  <br>
	
	<form action = "<%= request.getContextPath()%>/register" method = "post">
		 Employee ID: <input type = "number" name = "empid"> <br> <br>
		 Employee Name: <input type = "text" name = "empname"> <br> <br> 
		 Employee Contact: <input type = "text" name = "contact"> <br> <br>
		 Employee Designation: <input type = "text" name = "designation"> <br> <br>
		 Employee Email: <input type = "text" name = "email"> <br> <br>
		
		 <input type = "submit" value = " Register">
	
	</form>
	
	
	<br><br><br>
	
	<h3>Display Employees</h3> <br>
	<form action = "<%= request.getContextPath()%>/display" method = "post">
		
		
		<input type = "submit" value = "Display">
		
	</form>
	
	<br><br><br>
	
	<form action = "<%= request.getContextPath()%>/delete" method = "post">
	
		<h3>DELETE EMPLOYEE</h3>
		Enter the Employee ID: <input type = "number" name = "empid"> <br>
		
		<input type = "submit" value = "Delete">
	</form>
	
	
	
</body>
</html>