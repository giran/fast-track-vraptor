<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="logo">Movy</div>

<div id="logado">Ol�, ${sessionScope.userSession.user.nome} (<a href="<c:url value='/logout'/>">sair</a>)</div>