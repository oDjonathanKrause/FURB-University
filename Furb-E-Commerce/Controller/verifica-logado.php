<?php

	session_start();
	
	if((!isset ($_SESSION['login']) == true) and (!isset ($_SESSION['senha']) == true))
	{
		unset($_SESSION['login']);
		unset($_SESSION['senha']);
		echo "nao logado";
	} else 
	{
		$logado = $_SESSION['login'];
		echo $logado;
	}
?>
