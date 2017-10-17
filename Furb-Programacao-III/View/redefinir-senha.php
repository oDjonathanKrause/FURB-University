<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Redefenir Senha</title>
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
		
		
	<div style="padding-top: 10%; padding-left: 10%;">
		<h2> Redefenir Senha </h2>
		
		<form method="POST" action="..\Controller\reseta-senha.php" >
			<div class="form-group">
				<div class="col-xs-7">
					<label for="reseta-senha">Digite seu e-mail:</label>
					<input class="form-control" name="email" id="email" type="email" required>
				</div>

				<div class="col-xs-7" style="padding-top: 3%;">
					<label for="reseta-senha">Senha atual:</label>
					<input class="form-control" name="senhaAtual" id="senhaAtual" type="password" required>
				</div>

				<div class="col-xs-7" style="padding-top: 3%;">
					<label for="reseta-senha">Nova senha:</label>
					<input class="form-control" name="senhaNova" id="senhaNova" type="password" required>
				</div>
			</div>
			
			<div class="col-md-12 col-sm-12 col-xs-12"></div>
			
			<div class="form-group">
				<div class="col-xs-2" style="padding-top: 2%;">
					<button type="submit" role="button" class="btn btn-default">Redefinir</button>
				</div>
			</div>
		</form>
	</div>

	</div>
	<!-- fim main container --> 

	<!-- Include do footer -->
	<?php include("../Includes/footer-fixo.html");?>

</body>
</html>