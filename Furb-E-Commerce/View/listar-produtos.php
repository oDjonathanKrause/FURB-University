<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Estoque</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<!-- imports -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-hQpvDQiCJaD2H465dQfA717v7lu5qHWtDbWNPvaTJ0ID5xnPUlVXnKzq7b8YUkbN" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
	<?php include("../Includes/navbar-logado.html");?>
	<div class="container">
		<div id="produtos" style="padding-top: 75px; width:45%">

	<?php 

	//Conexão com o banco
	$con = mysqli_connect("127.0.0.1:3307", "root", "", "ecommercefurb");

	// Checa conexão
	if ($con->connect_error)
		die("Connection error: " . mysqli_connect_error());

	//query 
	$query = "SELECT idProduto, nm_produto, qtd_estoque, vl_valor, ds_produto 
			  FROM produtos
			  ORDER BY 1 desc";

	$result = mysqli_query($con, $query);

	//executa a query
	if (mysqli_query($con, $query)) 
	{
		while($row = mysqli_fetch_array($result))
		{ 
			$idProduto = $row['idProduto'];
			$nm_produto = $row['nm_produto'];
			$qtd_estoque = $row['qtd_estoque'];
			$vl_valor =  $row['vl_valor'];
			$ds_produto = $row['ds_produto'];

			?>
			<form method="POST" action="../Controller/alterar-produto.php">
				<div class="list-group">
					<fieldset class="form-group">
						<a class="list-group-item" name="idProduto"> Código: <?php echo $idProduto; ?> 
        					<button class="btn btn-alert" type="submit">Alterar</button></a>
						<a class="list-group-item" name="nm_produto"> Nome: <?php echo $nm_produto; ?> </a> 	 
						<a class='list-group-item' name="qtd_estoque"> Quantidade: <?php echo $qtd_estoque; ?> </a>
						<a class='list-group-item' name="vl_valor"> Valor: <?php echo $vl_valor; ?> </a>
						<a class='list-group-item' name="ds_produto"> Descrição: <?php echo $ds_produto; ?> </a>
        					
        				<!-- Parametros enviados por POST para alterar-produto.php -->
        				<input name="idProduto" value="<?php echo $idProduto; ?>" type="hidden">
        				<input name="nm_produto" value="<?php echo $nm_produto; ?>" type="hidden">
        				<input name="qtd_estoque" value="<?php echo $qtd_estoque; ?>" type="hidden">
        				<input name="vl_valor" value="<?php echo $vl_valor; ?>" type="hidden">
        				<input name="ds_produto" value="<?php echo $ds_produto; ?>" type="hidden">
					</fieldset>
				</div>


			</form>
			<?php
		}
	}
	else 
	    echo "Erro: " . $query . "<br>" . mysqli_error($con);

	//fecha a conexão
	mysqli_close($con);

	?>
		
		</div>
	</div> <!-- fim container -->



</body>
</html>


 
