<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.skabold.wildlyne.main.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="nouveauJoueurAction.jsp" method="post">
		Pseudonyme :<input type=text name="pseudo" /><br>
		E-mail :<input type=text name="email"/><br>
		Mot de passe :<input
			type=password name="pwd1" /><br> 
			Retaper le mot de passe: <input type=password name="pwd2"/><br>
			<input type="submit"
			value="envoyer" />
	</form>
	<!--  message d'erreur éventuel -->
	<%=Game.getInstance().getMessage(session, "message_nouveauJoueur")%>

</body>
</html>