<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>menu</title>
<link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styles.css"/>'>
</head>
<body>
	<nav id="menu">
		<ul>
			<li class="menu-item"><a href="indexsecretaria">Home</a></li>
			<li class="menu-item"><a href="aluno">Aluno</a></li>
			<li class="menu-item"><a href="historico">Histórico Aluno</a></li>
			<li class="menu-item"><a href="secretariadispensa">Resolver Dispensas de Disciplina</a></li>
			<li class="menu-item"><a href="matricula">Matricula</a></li>
			<li class="menu-item right"><a href="index">Sair</a></li>
		</ul>
	</nav>
</body>
</html>
