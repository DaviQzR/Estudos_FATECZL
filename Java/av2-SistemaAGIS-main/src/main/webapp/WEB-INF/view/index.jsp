<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bem-Vindo ao AGIS</title>
<link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styles.css"/>'>
<header> <!-- Cabeçalho pra dar boas vindas pro usuario -->
	<h1 align="center">Bem Vindo ao AGIS</h1>
</header>
</head>
<body>
<div align="center"> 
<!--
Essa div tem dois botões, um para acessar a área da secretaria,
onde estarão todos os CRUDs, e a matrícula, onde o aluno
poderá realizar a matricula 
-->
	</br>
	</br>
	<h1 class="texto">Acesso ao sistema</h1>
	</br>
	</br>
	<div class="menu">
		<li><a href="indexsecretaria">Portal Secretaria</a></li>
		<li><a href="indexaluno">Portal Aluno</a></li>
		<li><a href="indexprofessor">Portal Professor</a></li>
	</div>
</div>
</body>
	<footer><b>Desenvolvido por Davi De Queiroz e Luiz Antonio</b></footer>
</html>