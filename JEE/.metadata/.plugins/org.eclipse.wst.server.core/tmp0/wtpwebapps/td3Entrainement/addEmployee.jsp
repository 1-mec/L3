<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="AddEmployeeServlet" method=post>
	Firstname : <input name=firstname type=text><br>
	Lastname : <input name=lastname type=text><br>
	Role : <input name=role type=text><br>
	<input value=send type=submit>
	</form>
</body>
</html>