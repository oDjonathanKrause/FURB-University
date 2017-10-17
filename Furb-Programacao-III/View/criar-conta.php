<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>E-Commerce Maneiro</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<!-- Imports -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-hQpvDQiCJaD2H465dQfA717v7lu5qHWtDbWNPvaTJ0ID5xnPUlVXnKzq7b8YUkbN" crossorigin="anonymous">
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/0.9.0/jquery.mask.min.js"></script>

	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
	<div class="container">

	<!-- Include da navbar -->
	<?php include("../Includes/navbar-logado.html");?>

		
	<!-- Criar conta -->
	<div align="center">
	<div id="criar-conta" align="center">
		<form id="form-contato" align="left" method="POST" action="..\Controller\cadastrar-pessoa.php" style="padding-bottom: 50px;">
			<fieldset class="form-group">
				<label for="formGroupExampleInput">Nome completo</label>
				<input type="text" class="form-control" name="nome" id="nome" placeholder="João da Silva Sauro">
			</fieldset>

			<fieldset class="form-group">
				<label for="formGroupExampleInput2">Email</label>
				<input type="text" class="form-control" name="email" id="email" placeholder="joaosarino@meuemail.com">
			</fieldset>
			
			<fieldset class="form-group">
				<label for="formGroupExampleInput2">CPF</label>
				<input type="text" class="form-control" name="cpf" id="cpf" placeholder="481.516.230-42">
			</fieldset>
			
			<fieldset class="form-group">
				<label for="formGroupExampleInput2">Login</label>
				<input type="text" class="form-control" name="login" id="login" placeholder="joao">
			</fieldset>

			<fieldset class="form-group">
				<label for="formGroupExampleInput2">Senha</label>
				<input type="password" class="form-control" name="senha" id="senha" placeholder="********">
			</fieldset>

			<!-- Botão enviar -->
			<div align="center">
				<input id="btnCadastrar" type="submit" class="btn btn-primary" role="button" />
			</div>
		</form>
	</div>	
	</div>
	

	</div> <!--container -->


	<!-- Include do footer -->
	<?php include("../Includes/footer-fixo.html");?>

	<script>

		//valida campos
		jQuery(function($) 
		{
		    $('#telefone').mask('(99) 9999-9999');
		    $('#celular').mask('(99) 9.9999-9999');
		    $('#cpf').mask('999.999.999-99');
		});

	</script>

</body>
</html>


 
