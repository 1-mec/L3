<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>

    <h1>Plouf</h1>

    <nav>
        <a href="AddCours.jsp">Ajouter un cours</a>
        <a href="ListeCours.jsp">Liste</a>
    </nav>
    <h3>Liste des cours</h3>
    

	<form method="post" action=AddCoursServlet>
	<table border=1>
		<tr><td>Intitulé</td><td><input type=text name=intitule></td></tr>
		<tr><td>Coef</td><td><input type=text name=coef></td></tr>
		<tr><td>Duree</td><td><input type=text name=duree></td></tr>
		<tr><td><input type=submit value=envoyer></td></tr>
	</table>
	</form>
</body>
</html>