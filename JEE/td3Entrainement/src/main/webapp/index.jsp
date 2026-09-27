<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion form</title>
</head>
<body>
<center>
	<form action="LoginServlet" method=post>
	username : <input name=username type=text><br>
	password : <input name=password type=text><br>
	<input type=submit value=connexion>
	</form>
</center>
</body>
</html>