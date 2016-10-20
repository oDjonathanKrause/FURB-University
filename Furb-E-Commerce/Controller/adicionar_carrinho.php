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
	$nm_produto = $_POST['nm_produto'];
	$nm_pessoa = $_SESSION['login'];
	$idProduto = $_POST['idProduto'];
	$idPessoa = $_SESSION['idPessoa'];

	//conxão com o banco
	include("../Includes/conexao-banco.php");
	
	//query
	$query = "call p_adiciona_carrinho('$idProduto', '$idPessoa')";

	//executa a query
	if (mysqli_query($con, $query)) 
	{
		//se der boa, vai pra página abaixo
	    //header("Location: /View/meu_carrinho.php");
	    ?>
	    <div class="container">
	    	<br><br>
	    	
			Produto <b> <?php echo $nm_produto; ?> </b> adicionado ao carrinho de <b> <?php echo $nm_pessoa ?> </b>
		<br> idPessoa: <?php echo $idPessoa ?> <br>
		<a id="vai_carrinho" href="../View/meu_carrinho_v2.php?idPessoa=<?php echo $idPessoa?>">Ver carrinho</a>

		<script>
			//clica no botao automagicamente e vai pro carrinho
			document.getElementById('vai_carrinho').click();
		</script>
		<?php
	}
	else 
	    echo "Erro: " . $query . "<br>" . mysqli_error($con);

	//fecha a conexão
	mysqli_close($con);


}


