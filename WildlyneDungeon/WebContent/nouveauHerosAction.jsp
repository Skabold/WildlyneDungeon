<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!--  Header � mettre sur toutes les pages pour g�rer la s�curit� -->
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
<title>Creation du heros</title>
</head>
<% Accueil.getInstance().creerHeros(session, response, request.getParameter("nom")); %>
</html>
<%}%>