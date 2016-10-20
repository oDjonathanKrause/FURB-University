
<?php

$idProduto = $_POST['idProduto'];
$nm_produto = $_POST['nm_produto'];
$vl_valor = $_POST['vl_valor'];
$qtd_estoque = $_POST['qtd_estoque'];
$ds_produto = $_POST['ds_produto'];

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


//chama procedure que edita o produto
$query = "CALL editarProduto('$idProduto', '$nm_produto', '$qtd_estoque', '$vl_valor', '$ds_produto')";

//executa a query
if (mysqli_query($con, $query)) 
	header('location: ../View/listar-produtos.php');
else 
    echo "Erro: " . $query . "<br>" . mysqli_error($con);

//fecha a conexão
mysqli_close($con);

?>