<?php

	$nome_promocao = $_POST['nome'];
	$desc_promocao = $_POST['detalhe'];
	$qt_porcent = $_POST['valor'];
	$dt_inicio = $_POST['dataInicio'];
	$dt_fim = $_POST['dataFim'];
	$status = $_POST['status'];

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

	//verifica o status
	if($status === "")
		$status = 1;
	else
		$status = 0;

	//query de insert
	$query = "INSERT INTO promocoes(idPromocao, nome_promocao, desc_promocao, qt_porcent, dt_inicio, dt_fim, status)
		  	  VALUES(null, '$nome_promocao', '$desc_promocao', '$qt_porcent', '$dt_inicio', '$dt_fim', '$status')";

	//executa a query
	if (mysqli_query($con, $query)) 
		//se der boa, vai pra página abaixo
	    header("Location: ../view/listar-promocoes.php");
	else 
	    echo "Erro: " . $query . "<br>" . mysqli_error($con);

	//fecha a conexão
	mysqli_close($con);

?>