<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wildlyne Dungeon - Page de connexion</title>
</head>
<body>
	<h1>Merci de vous identifier</h1>
	<form action="loginAction.jsp" method="post">
		Pseudonyme :<input type=text name="pseudo" /><br> Mot de passe :<input
			type=password name="pwd" /><br> <input type="submit"
			value="envoyer" />
	</form>
	<a href="nouveauJoueur.jsp">S'inscrire</a>
	<!--  message d'erreur éventuel -->
	<%=session.getAttribute("message")%>
</body>
</html>