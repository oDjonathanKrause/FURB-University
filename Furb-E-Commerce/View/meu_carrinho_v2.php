<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>E-Commerce Maneiro</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	 <!-- Bootstrap, JQuert, Javascript --> 
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-hQpvDQiCJaD2H465dQfA717v7lu5qHWtDbWNPvaTJ0ID5xnPUlVXnKzq7b8YUkbN" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

	<!-- Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Merriweather:400,700' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Josefin+Slab:700,600' rel='stylesheet' type='text/css'>

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="View/style.css">
</head>


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

$idPessoa = $_GET['idPessoa'];

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
	    	
	    	?>

	    	<div class="container" style="padding-top: 25px">
	    		<ul class="list-group">
				  <li class="list-group-item active"> Produto: <?php echo $produto ?> </li>
				  <li class="list-group-item"> Quantidade: <?php echo $quantidade ?> </li>
				</ul>

			</div>
	    	

	    	<?php 
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


?>

<div class="container">
	<form method='GET' action="../Controller/valida-cupom.php">
		<div class="input-group" style="padding-top: 30px; padding-left: 20px;">
				<input type="text" class="form-control" name='cupom' placeholder="Cupom de Desconto">
				<input  type="hidden" value="<?php echo $idPessoa; ?>" name="idPessoa">

			<span class="input-group-btn">
				<?php include("../controller/valida-cupom"); ?>
				<button class="btn btn-default" type="submit">Usar Cupom</button>
			</span>
		</div><!-- /input-group -->
	</form>

	<p> <?php 
			if(isset($_GET['desconto']))
			{
				$desconto = $_GET['desconto'];
			}

			if(!isset($desconto))
			{
				$desconto = 0;
			}
		?> 
	</p>
	
	<!-- Valor total -->
	<?php
		include("../Includes/conexao-banco.php");

		//query 
		$query = "select sum(prd.vl_valor)
					from produtos prd, itens_carrinho car
					where prd.idProduto = car.idProduto
					and car.idCarrinho in (select idCarrinho from carrinho carrinho 
										   where car.idCarrinho = carrinho.idCarrinho 
					                       and carrinho.idPessoa = $idPessoa);";

		//executa a query
		if (mysqli_query($con, $query)) 
		{
			$resultado = mysqli_query($con, $query);
			
			//verifica se retornou algum resultado
		    if(mysqli_num_rows($resultado) >= 1)
		    {
		    	//atribui o retorno da função para a variavel $preco
		    	$preco = mysqli_fetch_assoc($resultado);

		    	//divite o retorno(tira do array)
				$preco = implode(" ",$preco);

				//total
				$total = "R$" . $preco;
				$total_aux = $preco;
		    }
		    else 
		    {
		   		echo "produto nao encontrado";
		   	}
		}
		else 
		    echo "Erro: " . $query . "<br>" . mysqli_error($con);
	?>
	<br>
	
	<!-- Total -->
	<a>Total: <?php echo $total ?></a>
	<br>

	<!-- Desconto -->
	<a>Desconto: <?php echo $desconto . "%" ?> </a>
	<br>

	<!-- Total com desconto -->
	<?php
		//calculo
		$valor = $total_aux; 
	    $percentual = $desconto / 100.0;
	    $total_desconto = $valor - ($percentual * $valor);
	  
	    $total_desconto = round($total_desconto, 2);

	?>
	<a>Total com desconto: <?php echo "R$" . $total_desconto ?></a>
	<br>
	<a href="compra_finalizada.php?idCarrinho=<?php echo $carrinho?>
											    &idPessoa=<?php echo $idPessoa?>
											    &total=<?php echo $total_desconto?>">
	<!-- <a href="/compra_finalizada.php?idPessoa='$idPessoa'?idCarrinho='$carrinho'?total='$total_desconto'"> -->
		<button class="btn btn-default"> 
			Finalizar Compra 
		</button>
	</a>

	<a href="../index.php">
		<button class="btn btn-default"> 
			Comprar Mais 
		</button>
	</a>
</div>



