
<?php

	//$idPessoa = $_POST['idPessoa'];
	//$idCarrinho = $_POST['idCarrinho'];

	$idPessoa = 10;
	$idCarrinho = 1;

	//conxão com o banco
	include("../Includes/conexao-banco.php");
	
	//query
	$query = "INSERT INTO carrinho(idPedido, id_pessoa, carrinho_idCarrinho)
			  VALUES(null, '$idPessoa', '$idCarrinho')";

	//executa a query
	if (mysqli_query($con, $query)) 
	{
		echo "Compra efetuada com sucesso!";
	}
	else 
	{
	    echo "Erro: " . $query . "<br>" . mysqli_error($con);
	}

	//fecha a conexão
	mysqli_close($con);

}


