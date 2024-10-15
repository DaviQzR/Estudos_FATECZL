<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>AGIS - Aluno</title>
	<link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styles.css"/>'>
	</head>
<body>
    <header>
        <div>
            <jsp:include page="menualuno.jsp" />
        </div>
    </header>
    <h1 class="portal-aluno">Portal Aluno</h1>
</body>
</html>
