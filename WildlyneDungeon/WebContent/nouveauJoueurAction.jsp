<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.skabold.wildlyne.main.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Creation d'un nouveau joueur</title>
</head>
<body>
<%  Game.getInstance().getSecurite().createUser(session, response, request.getParameter("email"),
        request.getParameter("pseudo"), request.getParameter("pwd1"), 
        request.getParameter("pwd2")); %>
</body>
</html>