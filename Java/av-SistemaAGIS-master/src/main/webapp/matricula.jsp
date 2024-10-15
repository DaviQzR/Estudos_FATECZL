<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css">
<title>AGIS - Matricula</title>
<header>
	<h1 align="center">Gerenciamento de Matricula</h1>
	<div>
		<jsp:include page="menualuno.jsp" />
	</div>
</header>
</head>
<body>
	<h4 align="center">*período de matrícula: 15 a 21 de Janeiro / 15 a 21 de Julho</h4>
	<form action="matricula" method="post">
		<div align="center" class="container">
			<tr>
				<td colspan="3"><input class="input_data" type="text" id="ra"
					name="ra" placeholder="R.A" value="${aluno.ra }" maxlength="9"
					oninput="this.value = this.value.replace(/[^0-9]/g, '')">
				<td />
				<c:if test="${intervalo}">
					<td><input type="submit" id="botao" name="botao"
						value="Iniciar Matricula">
					<td />
				</c:if>
				<td><input type="submit" id="botao" name="botao"
					value="Consultar Matricula">
				</td>
			</tr>

		</div>
		</br>
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
		<div align="center">
			<c:if test="${not empty disciplinas and listar eq 'false'}">
				<table id="listaDisciplinas" class="table_round" align="center">
					<thead>
						<tr>
							<th></th>
							<th>Nome</th>
							<th>Qtd. Aulas</th>
							<th>Horário de Início</th>
							<th>Horário de Término</th>
							<th>Dia</th>
							<th>Situação</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="d" items="${disciplinas }">
							<tr>
								<td>
									<div>
										<c:if test="${d.situacao eq 'Não cursado' or d.situacao eq 'Reprovado'}">
											<input type="checkbox" name="disciplinasSelecionadas"
											value="${d.disciplina.codigo}">
										</c:if>
									</div>
								</td>
								<td><c:out value="${d.disciplina.nome}" /></td>
								<td><c:out value="${d.disciplina.qtdAulas}" /></td>
								<td><c:out value="${d.disciplina.horarioInicio}" /></td>
								<td><c:out value="${d.disciplina.horarioFim}" /></td>
								<td><c:out value="${d.disciplina.diaSemana}" /></td>
								<td><c:out value="${d.situacao}" /></td>
							</tr>
						</c:forEach>
						</br>
						<td><input type="submit" id="botao" name="botao"
							value="Confirmar Matricula">
						<td />
					</tbody>
				</table>
			</c:if>
			
			<c:if test="${not empty disciplinas and listar eq 'true'}">
				<table id="listaDisciplinas" class="table_round" align="center">
					<thead>
						<tr> 
							<th>Nome</th>
							<th>Qtd. Aulas</th>
							<th>Horário de Início</th>
							<th>Horário de Término</th>
							<th>Dia</th>
							<th>Situação</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="d" items="${disciplinas }">
							<tr>
								<c:if test="${d.situacao eq 'Em curso' or d.situacao eq 'Aprovado' }">
									<td><c:out value="${d.disciplina.nome}" /></td>
									<td><c:out value="${d.disciplina.qtdAulas}" /></td>
									<td><c:out value="${d.disciplina.horarioInicio}" /></td>
									<td><c:out value="${d.disciplina.horarioFim}" /></td>
									<td><c:out value="${d.disciplina.diaSemana}" /></td>
									<td><c:out value="${d.situacao}" /></td>
								</c:if>
							</tr>
						</c:forEach>
						</br>
					</tbody>
				</table>
			</c:if>
		</div>
	</form>
</body>
</html>