<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*" import="at.htlinn.ortner.db.*"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Verk&auml;ufe</title>
</head>
<body>

	<%ArrayList<PartSale> partsales = (ArrayList<PartSale>) session.getAttribute("partsales");%>
	<table border="1">
	  	<tr>
		  	<th>Verkauf ID</th>
		  	<th>Kunden ID</th>
		    <th>Produktname</th>
		    <th>St&uuml;ckzahl</th>
		  	<th>Preis pro Einheit</th>
		    <th>Verkaufsdatum</th>
	    </tr>
	    
		<%for(PartSale ps : partsales){%>
			<tr>
				<th><%= ps.getSaleID()%></th>
				<th><%= ps.getCustomerID()%></th>
				<th><%= ps.getProductName()%></th>
				<th><%= ps.getAmount()%></th>
				<th><%= ps.getPrice()%></th>
				<th><%= ps.getSellDate()%></th>
			</tr>
		<%
		}%>
	</table>
	
	
	<form action="WorkOnSales" method="get">
	<fieldset>
	<legend>Neuer Verkauf</legend>
	<label>Kunde: </label>
	<select name="list">
		<%for(Customer c:(ArrayList<Customer>)session.getAttribute("customers")){
			out.print("<option>" + c.getName() + "</option>");
		}%>
	</select><br>
	
		<label>St&uuml;ckzahl</label>
		<input type="text" name="saleAmount"><br>
		<label>Produktname</label>
		<input type="text" name="salePname"><br>
		<label>Preis pro Einheit</label>
		<input type="text" name="salePPE"><br>
		
	<label>Hersteller:</label><br>
		<input type="radio" name="producer" value="a">Samsung<br>
		<input type="radio" name="producer" value="b">Apple<br>
		<input type="radio" name="producer" value="c">HTC<br>
		<input type="radio" name="producer" value="d">Other<input type="text" name="other"><br>
	</fieldset>
	
	
	<fieldset>
		<legend>Verkauf l&ouml;schen</legend>
		Verkauf ID:
		<input type="text" id="saleID">
		<input type="submit" value="L&ouml;sche Verkauf" onClick="session.add">
	</fieldset>
	
	
	<fieldset>
	<legend>Verkauf bearbeiten</legend>
	</fieldset>
	
	<input type="submit" value="Abschicken">
	</form>
</body>
</html>