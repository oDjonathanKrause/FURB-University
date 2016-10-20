<html>
<head>
	<title>E-Commerce Maneiro</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	 <!-- Bootstrap, JQuert, Javascript --> 
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-hQpvDQiCJaD2H465dQfA717v7lu5qHWtDbWNPvaTJ0ID5xnPUlVXnKzq7b8YUkbN" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

	<!-- Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Merriweather:400,700' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Josefin+Slab:700,600' rel='stylesheet' type='text/css'>

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="View/style.css">
</head>

<?php

session_start();

if(!isset($_SESSION['login']))
{
	session_destroy();

	unset($_SESSION['login']);
	unset($_SESSION['senha']);
	unset($_SESSION['ie_tipo']);

	echo "Voce precisa estar logado";
}
else
{
	if($_SESSION['ie_tipo'] == 3)
	{
      	header("location: ../View/admin.php"); 
	}
	else
	{
		?>
			<div class="container">
				
				<br><br><br><br>
				Voce nao tem permissao para acessar esta pagina
				
				<br>
				<?php echo "<a href='../index.php'>Voltar</a>"; ?>
			</div>
<?php
	}
}

?>