
<?php

//busca produto pelo id
	
	//conxão com o banco
	$server = "127.0.0.1:3307";
	$user = "root";
	$password = "";
	$db = "ecommercefurb";

	//Cria conexão
	$con = mysqli_connect($server, $user, $password, $db);

	// Checa conexão
	if ($con->connect_error)
		die("Connection error: " . mysqli_connect_error());

	//query
	$query = "select * from produtos where idProduto = $cod";

	//executa a query
	$result = mysqli_query($con, $query);

	//taca num array
	$select = mysqli_fetch_array($result);
	
	//define o caminho da imagem 
	$caminho = "imagens\\" . $select['imagem'];
	
	$nmProduto = $select['nm_produto'];
	
	$preco = $select['vl_valor'];
	
	$parcelas = $select['parcelas'];

	$parcelado = $preco / $parcelas;
	
	$valoresParcela = "Ou ". $parcelas . "x Sem juros de R$" . $parcelado;
	
	$dsProduto = $select['ds_produto'];
	
	$imagem =  '<img src="' . $caminho . '" class="img-responsive"/>';

	
	//fecha a conexão
	mysqli_close($con);
	
?>