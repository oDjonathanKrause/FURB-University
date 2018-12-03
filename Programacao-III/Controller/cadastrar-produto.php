<?php

	$nm_produto = $_POST['nome'];
	$ds_produto = $_POST['descricao'];
	$vl_valor = $_POST['preco'];
	$parcelas = $_POST['parcelas'];
	$qtd_estoque = $_POST['quantidade'];
	$imagem = $_POST['imagem'];

	//Conexão com o banco
	$server = "127.0.0.1:3307";
	$user = "root";
	$password = "";
	$db = "ecommercefurb";

	//Cria conexão
	$con = mysqli_connect($server, $user, $password, $db);

	// Checa conexão
	if ($con->connect_error)
		die("Connection error: " . mysqli_connect_error());

	//query de insert
	$query = "INSERT INTO produtos(idProduto, nm_produto, ds_produto, vl_valor, parcelas, qtd_estoque, imagem)
		  	  VALUES(null, '$nm_produto', '$ds_produto', '$vl_valor', '$parcelas', '$qtd_estoque', '$imagem')";

	//executa a query
	if (mysqli_query($con, $query)) 
		//se der boa, vai pra página abaixo
	    header("Location: ..\View\produtos.php");
	else 
	    echo "Erro: " . $query . "<br>" . mysqli_error($con);

	//fecha a conexão
	mysqli_close($con);

?>