<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>E-Commerce Maneiro</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<!-- Imports -->
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
		
	<!-- Login -->
	<div align="center">
	<div id="pag-login" align="center">
		<form id="form-contato" align="left"  method="POST" action="login.php">
			<fieldset class="form-group">
				<label for="formGroupExampleInput2">Email</label>
				<input type="text" class="form-control" id="email" placeholder="joaosarino@meuemail.com">
			</fieldset>
			
			<fieldset class="form-group">
				<label for="formGroupExampleInput2">senha</label>
				<input type="password" class="form-control" id="senha" placeholder="********">
			</fieldset>
		</form>

	</div>
	</div>
	<div align="right" style="padding-right: 35%">
		<a href="login.php" id="btnLogin" type="button" class="btn btn-primary" role="button">Login</a>
	</div>
	
		


	</div>

	<!-- Include do footer -->
	<?php include("../Includes/footer-fixo.html");?>
</body>
</html>


 
