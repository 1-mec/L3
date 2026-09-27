<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.univ.tours.jee.entrainement.td3.Database"%>
<%@ page import="fr.univ.tours.jee.entrainement.td3.Employee"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%Database db = (Database)request.getServletContext().getAttribute("database"); %>
	<center>
		<table>
			<thead><tr> Firstame </tr><tr> LastName </tr><tr> Role </tr><tr> Edit </tr><tr> Delete </tr></thead>
			<tbody>
				<% Map<String,Employee> map = (Map<String,Employee>)db.getAllEmployees();  %>
				<% Iterator<Map.Entry<String,Employee>> it = map.entrySet().iterator(); 
				   while(it.hasNext()){
					   Employee emp = it.next().getValue();
				%>
					   <tr>
					   		<td>
					   			<th>
					   				<li><%= emp.Firstname() %>
					  			</th>
					   		</td>
					   		<td>
					   			<th>
					   				<%= emp.Lastname() %>
					  			</th>
					   		</td>
					   		<td>
					   			<th>
					   				<%= emp.Role() %>
					  			</th>
					   		</td>
					   		<td>
					   			<th>
									<!--<a href=modifyEmployee.jsp><button>Modify</button></a>-->
					  			</th>
					   		</td>
					   		<td>
					   			<th>
									<!-- <a href=deleteEmployee.jsp><button>Delete</button></a> -->
					  			</th>
					   		</td>
					   	</tr>
				  <% }%> 				
			</tbody>
			<tfoot>
				<br><a href=addEmployee.jsp><input value=add type=submit></a>
			</tfoot>
		</table>
	</center>
</body>
</html>