<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
	<head>
		<title>List Customers</title>
		
		<!-- Reference our stylesheet -->
		
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
	</head>
	
	<body>
		
		<div id="wrapper">
			<div id="header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div>
		</div>
		
		<div id="container">
		
			<div id="content">
			
			<!-- Add a new buttom : Add Customer -->
			<input type="button" value="Add Customer" 
					onClick="window.location.href='showFormForAdd'; return false;"
					class="add-button"
			/>
			
			<!-- add html table here -->
			
			<table>
			
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>
				
				<!-- Loop and print customers here -->
				<c:forEach var="tempCustomer" items="${customers}">
				
				<tr>
					<td> ${tempCustomer.firstName} </td>
					<td> ${tempCustomer.lastName} </td>
					<td> ${tempCustomer.email} </td>
				</tr>
				
				</c:forEach>
				
			</table>
			
			</div>
			
		</div>	
		
	</body>
	
</html>