<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Movy | ${artista.nome}</title>
	</head>
	<body>
		<fieldset style="width: 430px;">
			<legend>Artista</legend>

			<form action="<c:url value='/artista'/>" method="post">
				<input type="hidden" name="artista.id" value="${artista.id}"/>
				<input type="hidden" name="artista.imagem" value="${artista.imagem}"/>

				<label>Nome:</label>
				<input type="text" name="artista.nome" value="${artista.nome}"/><br/><br/>

				<input type="submit" value="salvar" class="button right"/>
			</form>
		</fieldset>
	</body>
</html>