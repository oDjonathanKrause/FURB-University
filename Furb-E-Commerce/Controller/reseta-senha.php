<?php

$email = $_POST['email'];
$senhaAtual = $_POST['senhaAtual'];
$senhaNova = $_POST['senhaNova'];

//Cria conexão
$con = mysqli_connect("127.0.0.1:3307", "root", "", "ecommercefurb");

// Checa conexão
if ($con->connect_error)
	die("Connection error: " . mysqli_connect_error());

//query de insert
$query = "UPDATE pessoa
		  SET senha = '$senhaNova'
		  WHERE email = '$email' 
		  AND senha = '$senhaAtual'";

if (mysqli_query($con, $query)) 
{
	$query = "SELECT nm_pessoa FROM pessoa 
			  WHERE email = '$email' 
			  AND senha = '$senhaAtual'";
}
else
{
	echo "Erro: " . $query . "<br>" . mysqli_error($con);
}

//fecha a conexão
mysqli_close($con);

?>