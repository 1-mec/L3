<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center><h1>Welcome <%= session.getAttribute("username") %></h1></center>
	<form action="WelcomeServlet" method=post>
		<input type=submit value="Liste Employés">
	</form>
	<form action=index.jsp method=<% session.invalidate(); %>>
		<input type=submit value=Deconnexion>
	</form>
</body>
</html>