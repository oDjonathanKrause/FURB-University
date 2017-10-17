<?php


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

?>