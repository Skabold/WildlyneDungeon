<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.skabold.wildlyne.main.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Validation du mot de passe</title>
</head>
<%  Game.getInstance().getSecurite().checkLogin(session, response, request.getParameter("pseudo"), request.getParameter("pwd")); %>
</html>