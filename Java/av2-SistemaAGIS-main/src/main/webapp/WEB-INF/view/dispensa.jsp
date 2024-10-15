<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href='<c:url value = "./resources/css/styles.css"/>'>
<title>AGIS - Requisição de Dispensa</title>
<header>
	<div>
		<jsp:include page="menualuno.jsp" />
	</div>
</header>
</head>
<body>
	<h1 class="requisicao-dispensa">Requisição de Dispensa</h1>
	<div align="center" class="container">
		<form action="dispensa" method="post">
			<table>
				<tr>
					<td colspan="3"><input class="input_data" type="text" id="ra"
						name="ra" placeholder="R.A" value="${aluno.ra}" maxlength="9"
						oninput="this.value = this.value.replace(/[^0-9]/g, '')">
						<br> <input type="submit" class="consultar-botao"
						name="botao" value="Consultar Aluno"></td>
				</tr>
			</table>
			<div align="center">
				<c:if test="${not empty disciplinas }">
					<table>
						<tr>
							<td colspan="3"><select class="input_data" id="disciplina"
								name="disciplina">
									<option value="0">Escolha uma Disciplina</option>
									<c:forEach var="d" items="${disciplinas }">
										<option value="${d.codigo }" selected="selected">
											<c:out value="${d.nome }" />
										</option>
									</c:forEach>
							</select>
							<td />
						</tr>
						<tr>
							<td colspan="3"><input class="input_data" type="text"
								id="ra" name="motivo" placeholder="Motivo da Dispensa" value="">
							<td />
							<td><input type="submit" id="botao" name="botao"
								value="Solicitar Dispensa"></td>
						</tr>
					</table>
				</c:if>
			</div>
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
				<c:if test="${not empty dispensas }">
					<table class="table-round">
					<h2><b>Solicitações Realizadas</b></h2>
					</br>
						<thead>
							<tr>
								<td>Disciplina</td>
								<td>Motivo</td>
								<td>Estado</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="d" items="${dispensas }">
								<tr>
									<td><c:out value="${d.disciplina.nome }"/></td>
									<td><c:out value="${d.motivo }"/></td>
									<td><c:out value="${d.estado }"/></td>
								</tr>
							</c:forEach>
						</tbody>	
					</table>
				</c:if>
			</div>
		</form>
	</div>
</body>
</html>