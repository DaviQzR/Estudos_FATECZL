<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styles.css"/>'>
<title>AGIS - Aula</title>
<header>
	<h1 align="center">Gerenciamento de Aulas</h1>
	<div>
		<jsp:include page="menuprofessor.jsp" />
	</div>
</header>
</head>
<body>
	<div align="center" class="container">
		<form action="aula" method="post">
			<table>
				<tr>
					<td>
						<select class="input_data" id="disciplina" name="disciplina">
								<option value="0">Escolha uma Disciplina</option>
								<c:forEach var="d" items="${disciplinas }">
										<option value="${d.codigo }">
											<c:out value="${d.nome }"/>
										</option>									
								</c:forEach>
								<tr>
								<td colspan="4"><input class="input_data" type="date"
									id="dataAula" name="dataAula" 
									value='<c:out value="${dataAula}"></c:out>'>
								</td>
							</tr>
						</select>
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Iniciar Chamada">
					<td />
				</tr>
				</br>
				</tr>
				<div align="center">
					<c:if test="${not empty erro }">
						<H2>
							<b><c:out value="${erro }" /></b>
						</H2>
					</c:if>
				</div>
				<div align="center">
					<c:if test="${not empty saida }">
						<H2>
							<b><c:out value="${saida }" /></b>
						</H2>
					</c:if>
				</div>
			</table>
			</br>
			<div>
				<H2><b>Aula - </b><c:out value="${disciplina.nome }"></c:out></H2>
				<c:if test="${not empty alunos }">
					<table class="table_round">
						<thead>
							<tr>
								<th>Nome</th>
								<th>RA</th>
								<th>Presença</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="a" items="${alunos }">
							<tr>
								<td><c:out value="${a.nome }"></c:out></td>
								<td><c:out value="${a.ra }"></c:out></td>
								<td>
								<c:choose>
									<c:when test="${disciplina.qtdAulas == 2 }">
										<select class="input-data" name="presenca" id="presenca">
											<option value="0">0</option>
											<option value="1">1</option>
											<option value="2">2</option>
										</select>
									</c:when>
									<c:otherwise>
										<select class="input-data" name="presenca" id="presenca">
											<option value="0">0</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
										</select>
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
							</c:forEach>
							<td align="center"><input type="submit" id="botao" name="botao" value="Finalizar Chamada" ></td>
						</tbody>
					</table>
				</c:if>
			</div>
		</form>
	</div>
</body>
</html>