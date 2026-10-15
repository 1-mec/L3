<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des cours</title>
</head>
<body>

    <h1>Plouf</h1>

    <nav>
        <a href="AddCours.jsp">Ajouter un cours</a>
        <a href="CoursServlet">Liste</a>
    </nav>

    <h3>Liste des cours</h3>

    <table border="1">
        <thead>
            <tr>
                <th>Id</th>
                <th>Intitulé</th>
                <th>Coefficient</th>
                <th>Durée</th>
                <th>Actions</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="entry" items="${map}">
                <tr>
                    <td>
                        <c:out value="${entry.value.Cours_id}" />
                    </td>

                    <td>
                        <c:out value="${entry.value.Intitule}" />
                    </td>

                    <td>
                        <c:out value="${entry.value.Coef}" />
                    </td>

                    <td>
                        <c:out value="${entry.value.Duree}" />
                    </td>

                    <td>
                        <a href="ModifyServlet?id=${entry.value.Cours_id}">
                            Supprimer
                        </a>

                        <a href="modify.jsp?id=${entry.value.Cours_id}">
                            Modifier
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
