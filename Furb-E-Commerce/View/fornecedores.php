<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Cadastro Fornecedores</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<!-- Imports -->
	<meta charset="utf-8">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-hQpvDQiCJaD2H465dQfA717v7lu5qHWtDbWNPvaTJ0ID5xnPUlVXnKzq7b8YUkbN" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>

	<script>
	</script>

	<div class="container">
		<!-- Include da navbar -->
		<?php include("../Includes/navbar-logado.html");?>

		
		<!-- divisor1 -->
		<div id="divisor1"></div>
		
		<!-- formulário -->	
		<div class="container" style="padding-top: 5%;">
			<h2> Fornecedores </h2>
			<form role="form" method="POST" action="../Controller/cadastrar-fornecedor.php">
				<div class="form-group">
					<div class="col-xs-3">
						<label for="nome">Nome:</label>
						<input class="form-control" name="nome" id="nome" type="text" required>
					</div>
				</div>
				<div class="col-md-12 col-sm-12 col-xs-12"></div>
				<div class="form-group">
					<div class="col-xs-3">
						<label for="cpf">CPF:</label>
						<input class="form-control" name="cpf" id="cpf" type="number" required>
					</div>
				</div>
				
				<div class="col-md-12 col-sm-12 col-xs-12"></div>
				
				<div class="form-group">
					<div class="col-xs-3">
						<label for="cnpj">CNPJ:</label>
						<input class="form-control" name="cnpj" id="cnpj" type="number" required>
					</div>
				</div>

				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="col-xs-3">
						<label for="comp">Descrição:</label>
						<input class="form-control" name="descricao" id="comp" type="text">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-xs-6">
						<label for="comp">Contato:</label>
						<input class="form-control" name="contato" id="comp" type="text">
					</div>
				</div>
				
				<div class="col-md-12 col-sm-12 col-xs-12"></div>
				
				<div class="col-md-12 col-sm-12 col-xs-12"></div>

				<div class="col-md-12 col-sm-12 col-xs-12"></div>
				<div class="form-group">
					<div class="col-xs-2">
						<p> <br/> </p>
						<button type="submit" class="btn btn-default">Enviar</button>
					</div>
				</div>
			</form>
			
		</div>
	</br>		

    
	</div>
	<!-- Footer -->
    
	<!-- Include do footer -->
	<?php include("../Includes/footer-fixo.html");?>


</body>
</html>


 
