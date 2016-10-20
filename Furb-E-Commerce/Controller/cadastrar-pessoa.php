<?php

$nm_pessoa = $_POST['nome'];
$email = $_POST['email'];
$login = $_POST['login'];
$senha = $_POST['senha'];
$cpf = $_POST['cpf'];

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
$query = "INSERT INTO pessoa(idPessoa, ie_tipo, nm_pessoa, login, senha, cpf, email)
	  	  VALUES(0, 0, '$nm_pessoa', '$login', '$senha', '$cpf', '$email')";

//executa a query
if (mysqli_query($con, $query)) 
    header("Location: ..\index.php");
else 
    echo "Erro: " . $query . "<br>" . mysqli_error($con);

//fecha a conexão
mysqli_close($con);

?>