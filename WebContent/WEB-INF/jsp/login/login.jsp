<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Movy | Login</title>

		<link type="text/css" rel="stylesheet" href="<c:url value='/css/reset.css'/>"/>
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/login.css'/>"/>
	</head>
	<body>
		<div id="logo">Movy</div>

		<c:if test="${error != null}">
			<div id="error">${error}</div>
		</c:if>

		<div id="login-wrapper">
			<div id="formulario">
				<form action="<c:url value='/login'/>" method="post">
					<label>E-mail:</label>
					<input type="text" name="usuario.email"/><br/>
	
					<label>Senha:</label>
					<input type="text" name="usuario.senha"/><br/>
	
					<input type="submit" value="acessar" class="button"/>
				</form>
			</div>
		</div>
	</body>
</html>