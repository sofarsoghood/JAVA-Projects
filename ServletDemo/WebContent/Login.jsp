<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="at.htlinn.ortner.servlets.*" 
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action="LoginCheck" method="get">
		<h2>Bitte Melden Sie sich in der Verwaltungssoftware an:</h2>
		<div>
			Username:
			<input type="text" name="username">
		</div>
		<div>Passwort:
		<input type="password" name="password">
		</div>
		<input type="submit" value="Login">
	</form>
</body>
</html>