<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Movy | Usu&aacute;rio [Listagem]</title>
	</head>
	<body>
		<c:if test="${not empty message}">
			${message}<br/><br/>
		</c:if>

		<c:forEach items="${usuarioList}" var="usuario">
			<table>
				<tbody>
					<tr>
						<td width="130" rowspan="4">
							<img src="<c:url value="/img/no-image.jpg"/>" alt="${usuario.nome}" width="120" height="130"/>
						</td>
					</tr>
					<tr>
						<td width="700px" class="titulo">
							<h2>${usuario.nome}</h2>
						</td>
					</tr>
					<tr>
						<td class="subtitulo">(${usuario.email} - ${usuario.senha})</td>
					</tr>
					<tr>
						<td width="500">
							<form action="<c:url value='/usuario/${usuario.id}'/>" method="get" class="left">
								<input type="submit" value="exibir" class="button"/>
							</form>

							<form action="<c:url value='/usuario'/>" method="post" class="left">
								<input type="hidden" name="_method" value="put"/>
								<input type="hidden" name="usuario.id" value="${usuario.id}"/>
				
								<input type="submit" value="editar" class="button"/>
							</form>

							<form action="<c:url value='/usuario'/>" method="post" class="left">
								<input type="hidden" name="_method" value="delete"/>
								<input type="hidden" name="usuario.id" value="${usuario.id}"/>
				
								<input type="submit" value="remover" class="button"/>
							</form><br/>
						</td>
					</tr>
				</tbody>
			</table><br/><br/>
		</c:forEach>
	</body>
</html>