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
<div class="container" style="padding-top: 50">
<?php

$cupom = $_GET['cupom'];
$idPessoa = $_GET['idPessoa'];

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

//query 
$query = "SELECT f_validaCupom('$cupom')";

//executa a query
if (mysqli_query($con, $query)) 
{
	$resultado = mysqli_query($con, $query);
	
	//verifica se retornouo algum resultado
    if(mysqli_num_rows($resultado) >= 1)
    {
    	//atribui o retorno da função para a variavel $desconto
    	$desconto = mysqli_fetch_assoc($resultado);

    	//divite o retorno(tira do array)
		$desconto = implode(" ",$desconto);

		//ver carrinho
		echo "Voce ganhou " . $desconto . '%' . " de desconto ";  ?>
        <br>
        <a href="../View/meu_carrinho_v2.php?desconto=<?php echo $desconto ?>&idPessoa=<?php echo $idPessoa?>">Ver carrinho</a>
        <?php
    }

    else 
    {
   		echo "promocao invalida";
   	}
}
else 
    echo "Erro: " . $query . "<br>" . mysqli_error($con);

//fecha a conexão
mysqli_close($con);

