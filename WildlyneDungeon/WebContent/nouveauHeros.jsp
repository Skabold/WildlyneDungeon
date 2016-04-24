<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!--  Header à mettre sur toutes les pages pour gérer la sécurité -->
<%@ page
	import="org.skabold.wildlyne.main.*, java.util.List, org.skabold.wildlyne.beans.*"%>
<%
    if (Game.getInstance().getSecurite().checkSecurite(session, response)) {
%>
<!-- ------------- -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Creation d'un heros</title>
</head>
<body>
	<h1>Créer un héros</h1>
	<form action="nouveauHerosAction.jsp">
	Nom : <input type="text" name="nom"><br>
	<input type="submit" name="Envoyer">	
	</form>
	<!--  message d'erreur éventuel -->
	<%=Game.getInstance().getMessage(session, "message_nouveauHeros")%>
	
</body>
</html>
<%}%>