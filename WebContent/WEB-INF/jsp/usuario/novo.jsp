<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Movy | ${usuario.nome}</title>
	</head>
	<body>
		<fieldset style="width: 430px;">
			<legend>Usu&aacute;rio</legend>

			<form action="<c:url value='/usuario'/>" method="post">
				<input type="hidden" name="usuario.id" value="${usuario.id}"/>
	
				<label>Nome:</label>
				<input type="text" name="usuario.nome" value="${usuario.nome}"/><br/>

				<label>E-mail:</label>
				<input type="text" name="usuario.email" value="${usuario.email}"/><br/>

				<label>Senha:</label>
				<input type="text" name="usuario.senha" value="${usuario.senha}"/><br/><br/>

				<input type="submit" value="salvar" class="button right"/>
			</form>
		</fieldset>
	</body>
</html>