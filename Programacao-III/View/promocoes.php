<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Cadastro Promoções</title>
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
			<h2> Promoções </h2>
			<form role="form" method="POST" action="../Controller/cadastrar-promocao.php">
				<div class="form-group">
					<div class="col-xs-3">
						<label for="promo">Promoção:</label>
						<input class="form-control" name="nome" id="promo" type="text"  required>
					</div>
				</div>
				<div class="col-md-12 col-sm-12 col-xs-12">
					<label for="detalhe">Detalhe:</label>
					<textarea class="form-control" rows="5" name="detalhe" id="detalhe"></textarea>
				</div>
				<div class="form-group">
					<div class="col-xs-2">
						<label for="preco1">Valor:</label>
						<input class="form-control" name="valor" id="porcent" type="number" min="0" max="999" step="0.01" required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-2">
						<label for="dtInicio">Data início:</label>
						<input class="form-control" name="dataInicio" id="dtInicio" type="date" required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-2">
						<label for="dtFinal">Data fim:</label>
						<input class="form-control" name="dataFim" id="dtFinal" type="date">
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-2 " >
						<div class="checkbox" style="padding-top:10%">
							<label class="checkbox-inline"> <input type="checkbox" name="status" value="">Ativo
							</label>
							</label>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-sm-12 col-xs-12"></div>
				<div class="form-group">
					<div class="col-xs-2" style="padding-bottom: 5%;">
						<p> <br/> </p>
						<button type="submit" class="btn btn-default" >Enviar</button>
					</div>
				</div>
			</form>
			
		</div>
</div>

	<!-- Include do footer -->
	<?php include("../Includes/footer-fixo.html");?>

</body>
</html>


 
