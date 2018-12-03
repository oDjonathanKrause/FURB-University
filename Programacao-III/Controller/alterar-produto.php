<?php

$idProduto = $_POST['idProduto'];
$nm_produto = $_POST['nm_produto'];
$vl_valor = $_POST['vl_valor'];
$qtd_estoque = $_POST['qtd_estoque'];
$ds_produto = $_POST['ds_produto'];

?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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

<body>
	<?php include("../Includes/navbar-deslogado.html"); ?>
<div class='container' style="padding-top: 75px;">
	<form method="POST" action="../Controller/alterar-produto-procedure.php">
	<div class="list-group">
		<a class="list-group-item" name="idProduto"> Código: <?php echo $idProduto; ?> <a>
			<input name="idProduto" type="hidden" value="<?php echo $idProduto ?>" placeholder="<?php echo $idProduto; ?>">

		<a class="list-group-item" name="nm_produto"> Nome: 
			<input name="nm_produto" placeholder="<?php echo $nm_produto; ?>"> </a>

		<a class='list-group-item' name="qtd_estoque"> Quantidade: 
			<input name="qtd_estoque" placeholder="<?php echo $qtd_estoque; ?>"> </a>

		<a class='list-group-item' name="vl_valor"> Valor: 
			<input name="vl_valor" placeholder="<?php echo $vl_valor; ?>"> </a>

		<a class='list-group-item' name="vl_valor"> Descrição: 
			<input name="ds_produto" placeholder="<?php echo $ds_produto; ?>"> </a>
	</div>
	<button class="btn btn-alert" type="submit">Alterar</button></a>
</form>
</body>
</html>

