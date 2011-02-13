<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Movy | Artista [Listagem]</title>
	</head>
	<body>
		<c:if test="${not empty message}">
			${message}<br/><br/>
		</c:if>

		<div id="consulta-wrapper">
			<input id="key" type="text" size="35" maxlength="40" title="Digite sua consulta aqui" onkeypress="consultarHandle(event);"/>
			<input type="button" value="buscar" title="Consultar artistas" onclick="consultar();" class="button"/>
		</div><br/><br/>

		<div id="content"></div>

		<script type="text/javascript">
			function consultar() {
				var $key	= $('input#key'),
					key		= ($key.val() != '') ? $key.val().toLowerCase() : '',
					pagina	= 1;

				$.ajax({
					type:		'get',
					dataType:	'json',
					url:		'<c:url value="/artista/pagina/"/>' + pagina,
					data:		'key=' + key,
					success:	function(artistaList) {
						var $content = $('div#content').empty();

						for (var i = 0; i < artistaList.length; i++) {
							$content.append(
								'<table>' +
									'<tbody>' +
										'<tr>' +
											'<td width="130" rowspan="3">' +
												'<img src="<c:url value="/artista/' + artistaList[i].id + '/imagem"/>" alt="' + artistaList[i].nome + '" width="120" height="130"/>' +
											'</td>' +
										'</tr>' +
										'<tr>' +
											'<td width="700px" class="titulo">' +
												'<h2>' + artistaList[i].nome + '</h2>' +
											'</td>' +
										'</tr>' +
										'<tr>' +
											'<td width="500">' +
												'<form action="<c:url value="/artista/' + artistaList[i].id + '/' + artistaList[i].nome.replace(' ', '-').toLowerCase() + '"/>" method="get" class="left">' +
													'<input type="submit" value="exibir" class="button"/>' +
												'</form>' +
		
												'<form action="<c:url value="/artista"/>" method="post" class="left">' +
													'<input type="hidden" name="_method" value="put"/>' +
													'<input type="hidden" name="artista.id" value="' + artistaList[i].id + '"/>' +
									
													'<input type="submit" value="editar" class="button"/>' +
												'</form>' +
		
												'<form action="<c:url value="/artista"/>" method="post" class="left">' +
													'<input type="hidden" name="_method" value="delete"/>' +
													'<input type="hidden" name="artista.id" value="' + artistaList[i].id + '"/>' +
									
													'<input type="submit" value="remover" class="button"/>' +
												'</form><br/>' +
											'</td>' +
										'</tr>' +
									'</tbody>' +
								'</table><br/><br/>');
						}
					},
					complete: function() {
						$key.val('');
					}
				});
			};

			function consultarHandle(evt) {
				if ((evt.keyCode ? evt.keyCode : evt.which) == 13) {
					consultar();
				}
			};
		</script>
	</body>
</html>