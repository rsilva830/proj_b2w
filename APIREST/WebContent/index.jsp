<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%
	String status = null;
	status = request.getParameter("status");
	int st = 0;
	if (status != null) {
		st = Integer.parseInt(status);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>API REST EM JAVA BY RAFAEL</title>
<script type="text/javascript" src='funcoesjs.js'></script>
<style>
h1 {
	margin-bottom: 1rem;
}

* {
	margin: 0;
	padding: 0;
}

.container {
	width: 100vw;
	height: 100vh;
	/*background: #6C7A89;*/
	display: flex;
	/*flex-direction: row;*/
	justify-content: center;
	align-items: top;
}

.formulario {
	width: 440px;
	height: 680px;

	border-radius: 15px;
	margin-bottom: 0em;
	margin-right: 0em;
	margin-left: 0em;
	margin-top: 1em;
	border: 1px solid #CCC;
	padding: 0.5em 1em 0.1em 1em;
	/*background: #F7F2E0;*/
	background: #B5B5B5;
}

body {
	margin: 0px;
}

fieldset {
	border: 0;
}

body, input, select, textarea, button {
	font-family: sans-serif;
	font-size: 0.9em;
}

.grupo:before, .grupo:after {
	content: " ";
	display: table;
}

.grupo:after {
	clear: both;
}

.campo {
	margin-bottom: 0.5em;
}

.campo label {
	margin-bottom: 0.1em;
	color: #666;
	display: block;
	/*font-size: 0.9em;*/
	/*border: 1px solid #CCC;*/
}

fieldset.grupo .campo {
	float: left;
	margin-right: 1em;
}

.campo input[type="text"], .campo input[type="email"], .campo input[type="url"],
	.campo input[type="tel"], .campo select, .campo textarea {
	padding: 0.1em;
	border: 1px solid #CCC;
	box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.2);
	display: block;
}

.campo select option {
	padding-right: 1em;
}

.campo input:focus, .campo select:focus, .campo textarea:focus {
	background: #FFC;
}

.campo label.checkbox {
	color: #000;
	display: inline-block;
	margin-right: 1em;
}

.botao {
	border-radius: 15px;
	/*font-size: 1em;*/
	/*background: #F5DA81;*/
	background: #696969;
	border: 0;
	margin-bottom: 1em;
	color: #FFF;
	padding: 0.2em 0.6em;
	box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.2);
	text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.5);
	font-weight: bold;
}

.botao:hover {
	background: #9C9C9C;
	box-shadow: inset 2px 2px 2px rgba(0, 0, 0, 0.2);
	text-shadow: none;
}

.botao, select, label.checkbox, select, label.checkbox {
	cursor: pointer;
}
</style>
</head>

<body>
	<div class="container">
		<div class="formulario">
			<h4>Adicionar um Planeta</h4>
			<form method="POST" action="ADICIONAPLANETA" name="form1"
				onsubmit="return validaDados(this);">
				<fieldset class="grupo">
					<div class="campo">
						<label for="nome">Nome</label> <input type="text" name="nome"
							style="width: 10em" id="nome" validar="1">
					</div>
					<div class="campo">
						<label for="nome">Clima</label> <input type="text" name="clima"
							style="width: 10em" id="clima" validar="1">
					</div>
					<div class="campo">
						<label for="nome">Terreno</label> <input type="text"
							name="terreno" style="width: 10em" id="terreno" validar="1">
					</div>
					<div class="campo">
						<label for="nome">Id Publico</label> <input type="text"
							name="idPublico" style="width: 5em" id="idPublico" validar="2">
					</div>
				</fieldset>
				<button type="submit" class="botao" name="submit">Adicionar</button>
			</form>
		<br />
			<form method="POST" action="PLANETA" name="form2"
				onsubmit="return validaDados(this);">
				<fieldset class="grupo">
					<div class="campo">
						<strong>Buscar Planeta por ID ou NOME</strong><input type="text"
							name="busca" style="width: 15em" value="" validar="2" />
					</div>
				</fieldset>
				<input type="submit" class="botao" value="Buscar" />
			</form>
		<br />
			<form method="POST" action="EXCLUIPLANETA" name="form4"
				onsubmit="return validaDados(this);">
				<fieldset class="grupo">
					<div class="campo">
						<strong>Excluir Planeta por ID</strong><input type="text"
							name="id" style="width: 5em" value="" validar="2" />
					</div>
				</fieldset>
				<input type="submit" class="botao" value="Excluir" />
			</form>
			
			<br/><br/>
			<form method="POST" action="LISTAPLANETAS" name="form5">
				<input type="submit" class="botao" value="Listar Planetas" />
			</form>
			<p>
				<strong>Resposta API</strong></strong>
			</p>
			<textarea name="resposta" rows="15" cols="60">
		
<%
			if (status != null) {
				out.print(session.getAttribute("resapi"));
			} else {
				out.print("");
			}
		%>
</textarea>
		</div>
	</div>

	<%
		switch (st) {
		case 1: {
			out.println("<script>alert('Operação realizada com sucesso!')</script>");
			break;
		}
		case 2: {
			out.println("<script>alert('Ocorreu um erro!')</script>");
			break;
		}
		}
	%>

</body>
</html>