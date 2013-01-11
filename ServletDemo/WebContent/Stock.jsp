<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*" import="at.htlinn.ortner.db.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stock</title>
</head>
<body>
	<p>Willkommen im Lager <%=session.getAttribute("username") %> (<a href="EditUser.jsp">Click here to edit information about you</a>) !<p>
	
	<h4>Derzeitige Lagerbestände:</h4>
	<table border="1">
		  	<tr>
		  	<th>#ID</th>
		  	<th>Modell</th>
		    <th>St&uuml;ck im Lager</th>
		    </tr>
		    <% ArrayList<PartStock> partstocks = (ArrayList<PartStock>) session.getAttribute("partstocks");
		    
		    for(PartStock ps:partstocks){%>
		    <tr>
		    	<th><%= ps.getId()%></th>
		    	<th><%= ps.getSmartphoneName()%></th>
		    	<th><%= ps.getAmount() %></th>
		    </tr>
		    <%
		    }
		    %>
		</table>
		<a href="Sales.jsp">Verk&auml;ufe</a>
</body>
</html>