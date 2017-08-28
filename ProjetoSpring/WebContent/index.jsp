<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="resources/css/css.css" />
<script language="javascript" src="resources/js/square.js"></script>
<script language="javascript" src="resources/js/jquery-1.9.1.js"></script>
<script language="javascript" src="resources/js/json.org.stringify.js"></script>
<title>Projeto Square</title>
</head>
<body>	
 	<fieldset>
 	<legend>Cadastrar Territórios</legend>	 	
	<ul>			
		<li>
			<label>Nome:</label>
			<input type="Text" id="nome" value="" maxlength="5" placeholder="Informe o nome">
		</li>
		<li>
			<label>Posição X:</label><br>
			<input type="Text" id="x_inicial" value="" maxlength="5" placeholder="X Inicial"> à 
			<input type="Text" id="x_final" value="" maxlength="5" placeholder="X Final">
		</li>
		<li>
			<label>Posição Y:</label><br>
			<input type="Text" id="y_inicial" value="" maxlength="5" placeholder="Y Inicial"> à 
			<input type="Text" id="y_final" value="" maxlength="5" placeholder="Y Final">
		</li>
		<li><br>
			<input type="button" onclick="projeto.save();" value="Cadastrar Território">				
		</li>			
	</ul>
	</fieldset>
	
	<fieldset>
 	<legend>Remover / Obter Dados ID:</legend>
	<ul>
		<li>
			<input type="text" id="id" name="id" placeholder="ID do território">
			<input type="button" onclick="projeto.remove();" value="Remover Território">
			<input type="button" onclick="projeto.find();" value="Obter Dados">
		</li>		
	</ul>
	</fieldset>
	
	<fieldset>
 	<legend>Pintar Território</legend>
	<ul>
		<li>
			<input type="text" id="x_pintar" name="x_pintar" placeholder="X">
			<input type="text" id="y_pintar" name="y_pintar" placeholder="Y">
			<input type="button" onclick="projeto.paint();" value="Pintar Território">
		</li>		
	</ul>
	</fieldset>
	
	<fieldset>
 	<legend>Listar Territórios</legend>
	<ul>
		<li>
			<input type="button" onclick="projeto.listAll();" value="Mostrar Terrritórios Cadastrados"><br>
			<div id="impressao" class="impressao"></div>		
		</li>
	</ul>	
	</fieldset>	
</body>
</html>