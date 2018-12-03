<?php
	
	include '../includes/conexao-banco.php';

	$idPessoa = $_POST['idPessoa'];
	$ie_tipo = $_POST['ie_tipo'];

	$query = "UPDATE pessoa
			  SET ie_tipo = '$ie_tipo'
			  WHERE idPessoa = '$idPessoa'";

	$result = mysqli_query($con, $query);

	header("location: ../view/lista-usuarios.php");
?>