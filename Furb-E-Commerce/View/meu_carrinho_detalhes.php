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

$idPessoa = 10;

//query 
$query = "select pes.nm_pessoa as Pessoa,
		   prd.ds_produto as Produto,
		   prd.idProduto as idProduto,
		   count(ica.idProduto) as Quantidade,
		   car.idCarrinho as Carrinho
		from pessoa pes, 
			 carrinho car,
			 itens_carrinho ica,
			 produtos prd
		where pes.idPessoa = car.idPessoa
		and ica.idCarrinho = car.idCarrinho
		and prd.idProduto = ica.idProduto
		and pes.idPessoa = '$idPessoa'
		group by pes.nm_pessoa,
			   prd.ds_produto,
			   car.idCarrinho,
			   prd.idProduto";

//executa a query
if (mysqli_query($con, $query)) 
{
	$result = mysqli_query($con, $query);

	if ($result->num_rows > 0) 
	{
	    while($row = $result->fetch_assoc()) 
	    {
	    	$pessoa = $row['Pessoa'];
	    	$produto = $row['Produto'];
	    	$quantidade = $row["Quantidade"];
	    	$carrinho = $row['Carrinho'];
	    	$idProduto = $row['idProduto'];

	        /* echo "Pessoa: " . $row["Pessoa"]. " - Produto: " . $row["Produto"] .
	             " - Quantidade: " . $row["Quantidade"] . " - Carrinho: " . $row["Carrinho"] . "<br>"; */
	    }

	    //header("Location: ../View/meu_carrinho.php?idPessoa=$idPessoa");
	} 
	else 
	{
	    echo "Nenhum resultado";
	}
}
else 
    echo "Erro: " . $query . "<br>" . mysqli_error($con);

//fecha a conexão
mysqli_close($con);




