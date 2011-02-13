<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Movy | ${artista.nome}</title>
	</head>
	<body>
		<c:forEach var="error" items="${errors}">
    		${error.category} - ${error.message}<br/>
		</c:forEach><br/><br/>

		<table>
			<tbody>
				<tr>
					<td width="130" rowspan="3">
						<img src="<c:url value='/artista/${artista.id}/imagem'/>" alt="${artista.nome}" width="120" height="130"/>
					</td>
				</tr>
				<tr>
					<td width="310" class="titulo">
						<h2>${artista.nome}</h2>
					</td>
				</tr>
				<tr>
					<td>
						<form id="form-imagem-filme" action="<c:url value='/artista/imagem'/>" enctype="multipart/form-data" method="post">
							<input type="hidden" name="artista.id" value="${artista.id}"/>

							<input type="file" name="file"/>
							<input type="submit" value="enviar" class="button"/>
						</form><br/><br/>
					</td>
				</tr>
				<c:if test="${artista.imagem ne 'default.jpg'}">
					<tr>
						<td colspan="2">
							<form action="<c:url value='/artista/${artista.id}/imagem'/>" method="post" class="action-remover-imagem">
								<input type="hidden" name="artista.id" value="${artista.id}"/>
								<input type="hidden" name="_method" value="delete"/>
	
								<input type="submit" value="remover imagem" class="button"/>
							</form>
						</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</body>
</html>