<?php

$nm_pessoa = $_POST['nome'];
$contato = $_POST['contato'];
$cpf = $_POST['cpf'];
$cnpj = $_POST['cnpj'];
$descricao = $_POST['descricao'];


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
$query = "CALL cadastrarFornecedor('$nm_pessoa', '$cpf', '$cnpj', '$contato', '$descricao')";

//executa a query
if (mysqli_query($con, $query)) 
    header("Location: ../view/fornecedores.php");
else 
    echo "Erro: " . $query . "<br>" . mysqli_error($con);

//fecha a conexão
mysqli_close($con);

?>