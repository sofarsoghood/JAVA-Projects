<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
import="at.htlinn.ortner.servlets.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit your Profile</title>
</head>
<body>
<form action="ProfilData" method="get">
<h2>Neue Daten im Formular eingeben:</h2>
<fieldset>
		<legend>Login Daten: </legend>
		<label for="nickname">Neuer Nickname: </label>
		<input name="nickname" type="text" accept="text"/><br/>
		<label for="password">Altes Passwort: </label>
		<input name="oldPassword" type="password" accept="text" /><br>
		<label for="password">Neues Passwort: </label>
		<input name="newPassword1" type="password" accept="text" /><br>
		<label for="password">Neues Passwort wiederholen: </label>
		<input name="newPassword2" type="password" accept="text" /><br>
	</fieldset>
	<fieldset>
		<legend>Name: </legend>
		<label for="name">Vorname: </label>
		<input name="name" type="text" id="vNfield" accept="text"/><br/>
		<label for="nachname">Nachname: </label>
		<input name="lastname" type="text" id="nNfield" accept="text"/><br/>
		<label for="confirmPW">Best&auml;tigen: </label>
		<input name="confirmPassword" type="password" id="confirmPW" accept="text"/><br/>
	</fieldset>
	<input type="submit" value="Speichern">
	<input type="reset" value="Zur&uuml;cksetzen">
	<p><a href="Stock.jsp">Zum Lager zur&uuml;ckkehren</a></p>
	</form>
</body>
</html>