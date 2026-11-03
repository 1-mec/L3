<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.Map" %>
<%@ page import="fr.univ.jee.tp_note.DataBase" %>
<%@ page import="fr.univ.jee.tp_note.Journal" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des cours</title>
</head>
<body>

    <h1>Journal</h1>


    <h3>Liste des cours</h3>

    <table border="1">
        <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Uri</th>
                <th>Date d'accès</th>
                <th>Accès authorisé</th>
            </tr>
        </thead>
<!-- 
         -->
        <tbody>
            <c:forEach var="entry" items="${map}">
                <tr>
                    <td>
                        <c:out value="${entry.key}" />
                    </td>

                    <td>
                        <c:out value="${entry.value.login}" />
                    </td>

                    <td>
                        <c:out value="${entry.value.uri}" />
                    </td>

                    <td>
                        <c:out value="${entry.value.date_acces}" />
                    </td>

                    <td>
                        <c:out value="${entry.value.acces_authorise}" />
                    </td>

                    <td>
                        <a href="ModifyServlet?id=${entry.value.acces_authorise}">
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
