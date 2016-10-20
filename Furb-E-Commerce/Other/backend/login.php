<?php 

//$login = $_POST['login'];
//$senha = MD5($_POST['senha']);

//Conexão com o banco
$server = "127.0.0.1:3307";
$user = "root";
$password = "";
$db = "ecommercefurb";

$con = mysqli_connect($server, $user, $password, $db);
// Checa conexão
if (!$con)
	die("Connection error: " . mysqli_connect_error());

$sql = "SELECT login, senha FROM pessoa WHERE login = '$login' AND senha = '$senha'";
$result = $con->query($sql);
/*
if ($result->num_rows > 0) 
{
	// output data of each row
	while($row = $result->fetch_assoc()) 
	{
		echo "login: " . $row["login"]. 
			 " - senha: " . $row["senha"].
	}
}

else 
	echo "0 results";
*/
?>

