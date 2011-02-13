<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>

		<title><decorator:title default="Movy | Your Movies Organized"/></title>
		
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/stylesheet.css'/>"/>

		<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
	</head>
	<body style="background-color: #${userSession.cor};">
		<div id="geral">
    	  	<div id="topo"><jsp:include page="../topo.jsp"/></div>
    	  	<div id="menu"><%@ include file="../menu.jsp" %></div>
      		
      		<div id="conteudo">
      			<decorator:body/>
			</div>
			
	      	<div id="rodape"><%@ include file="../rodape.jsp" %></div>
    	</div>
	</body>
</html>