<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<% // Header � mettre sur toutes les pages pour g�rer la s�curit� %>
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
<title>Insert title here</title>
</head>
<body>
	The Dreadful Wildlyne Dungeon is coming soon!
	<br>
	<%=Game.getInstance().hello(session)%>

	<!--  Liste des h�ros poss�d�s -->
	<%
	    final List<Heros> heroslist = Accueil.getInstance().getHeros(session);
	    if (heroslist.isEmpty()) {
	%>
	Vous n'avez pas de h�ros :
	<%
	    }
	    else {
	    	for (final Heros heros : heroslist) {
	%>
	Nom :
	<%=heros.getNom()%><br> Niveau :
	<%=heros.getNiveau()%><br>
	<hr>
	<%
	    	}
	    }
	%>
	<a href="nouveauHeros.jsp">Cr�er un h�ros</a>

</body>
</html>
<%
    }
%>