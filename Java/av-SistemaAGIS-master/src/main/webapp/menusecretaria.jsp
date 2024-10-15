<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>menu</title>
<link rel="stylesheet" href="./css/styles.css"> 
</head>
<body>
	<nav id="menu">
		<ul>
			<li class="menu-item"><a href="${pageContext.request.contextPath}/aluno">Aluno</a></li>
			<li class="menu-item"><a href="${pageContext.request.contextPath}/matricula">Matricula</a></li>
			<li class="menu-item right"><a href="viewchoose.jsp">Sair</a></li>
		</ul>
	</nav>
</body>
</html>
