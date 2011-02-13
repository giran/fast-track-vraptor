<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Movy | ${filme.titulo}</title>
	</head>
	<body>
		<c:forEach var="error" items="${errors}">
    		${error.category} - ${error.message}<br/>
		</c:forEach><br/><br/>

		<fieldset style="width: 430px;">
			<legend>Filme</legend>

			<form action="<c:url value='/filme'/>" method="post">
				<input type="hidden" name="filme.id" value="${filme.id}"/>
	
				<label>T&iacute;tulo:</label>
				<input type="text" name="filme.titulo" value="${filme.titulo}"/><br/>

				<label>Title:</label>
				<input type="text" name="filme.title" value="${filme.title}"/><br/>

				<label>Ano:</label>
				<input type="text" name="filme.ano" value="${filme.ano}"/><br/>

				<label>G&ecirc;nero:</label>
				<input type="text" name="filme.genero" value="${filme.genero}"/><br/>

				<label>Sinopse:</label>
				<textarea rows="6" cols="45" name="filme.sinopse">${filme.sinopse}</textarea><br/><br/>
	
				<input type="submit" value="salvar" class="button right"/>
			</form>
		</fieldset>
	</body>
</html>