<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Movy | Filme [Exibir]</title>
	</head>
	<body>
		<table>
			<tbody>
				<tr>
					<td width="130" rowspan="7"><img src="<c:url value="/img/no-image.jpg"/>" alt="${filme.titulo}" width="120" height="130"/></td>
				</tr>
				<tr>
					<td width="700px" class="titulo">
						<h2>${filme.titulo}</h2>
					</td>
				</tr>
				<tr>
					<td class="subtitulo">(${filme.title} - ${filme.genero} - ${filme.ano})</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="sinopse">${filme.sinopse}</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="500">
						<form action="<c:url value='/filme'/>" method="post" class="action-form">
							<input type="hidden" name="_method" value="put"/>
							<input type="hidden" name="filme.id" value="${filme.id}"/>
			
							<input type="submit" value="editar" class="button"/>
						</form>

						<form action="<c:url value='/filme'/>" method="post" class="action-form">
							<input type="hidden" name="_method" value="delete"/>
							<input type="hidden" name="filme.id" value="${filme.id}"/>
			
							<input type="submit" value="remover" class="button"/>
						</form><br/>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>