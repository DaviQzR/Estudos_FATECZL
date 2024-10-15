<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styles.css"/>'>
<title>menu</title>
</head>
<body>
	<nav id="menu">
		<ul>
			<li class="menu-item"><a href="indexaluno">Home</a>
			<li class="menu-item"><a href="matricula">Matricula</a>
			<li class="menu-item"><a href="dispensa">Solicitar Dispensa</a>
			<li class="menu-item"><a href="index">Sair</a>
		</ul>
	</nav>
</body>
</html>