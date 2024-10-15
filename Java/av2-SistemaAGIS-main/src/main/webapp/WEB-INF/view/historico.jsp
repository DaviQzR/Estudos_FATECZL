<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AGIS - Histórico</title>
<link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styles.css"/>'>
<header>
	<h1 align="center">Histórico do Aluno</h1>
	<div>
		<jsp:include page="menusecretaria.jsp" />
	</div>
</header>
</head>
<body>
	<form action="historico" method="post">
		<div align="center" class="container">
			<tr>
				<td colspan="3"><input class="input_data" type="text" id="ra"
					name="ra" placeholder="R.A" value="${aluno.ra }" maxlength="9"
					oninput="this.value = this.value.replace(/[^0-9]/g, '')">
				<td />
				<td><input type="submit" id="botao" name="botao"
					value="Consultar Historico">
				</td>
			</tr>
		</div>
		<br/>
		<div align="center">
			<c:if test="${not empty saida }">
				<H2>
					<b><c:out value="${saida }" /></b>
				</H2>
			</c:if>
		</div>
		<br />
		<div align="center">
			<c:if test="${not empty erro }">
				<H2>
					<b><c:out value="${erro }" /></b>
				</H2>
			</c:if>
		</div>
		</br>
			<c:if test="${not empty aluno }">
		<div align="center" class="container">
				<table class="table_round">
					<thead>
						<tr>
							<th>RA</th>
							<th>Nome Completo</th>
							<th>Nome do Curso</th>
							<th>Data Primeira Matricula</th>
							<th>Pontuação Vestibular</th>
							<th>Posição Vestibular</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><c:out value="${aluno.ra}" /></td>
							<td><c:out value="${aluno.nome}" /></td>
							<td><c:out value="${aluno.curso.nome}" /></td>
							<td><c:out value="${aluno.dataPrimeiraMatricula}" /></td>
							<td><c:out value="${aluno.pontuacaoVestibular}" /></td>
							<td><c:out value="${aluno.posicaoVestibular}" /></td>
						</tr>
					</tbody>
				</table>
				</br>
				<table class="table_round">
					<thead>
						<tr>
							<th>Cod. Disciplina</th>
							<th>Nome Disciplina</th>
							<th>Professor</th>
							<th>Qtd. de Faltas</th>
							<th>Nota Final</th>
							<th>Situação</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="d" items="${disciplinas }">
							<tr>
								<td><c:out value="${d.disciplina.codigo }" /></td>
								<td><c:out value="${d.disciplina.nome }" /></td>
								<td><c:out value="${d.disciplina.professor.nome }"/></td>
								<td><c:out value="${d.qtdFaltas }" /></td>
								<td><c:out value="${d.notaFinal }" /></td>
								<td><c:out value="${d.situacao }" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
			</c:if>
	</form>
</body>
</html>