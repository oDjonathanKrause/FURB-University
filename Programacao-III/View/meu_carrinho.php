<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Meu carrinho</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<!-- imports -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-hQpvDQiCJaD2H465dQfA717v7lu5qHWtDbWNPvaTJ0ID5xnPUlVXnKzq7b8YUkbN" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>

	<script language="Javascript">
	function janela() 
	{
		open("compra_finalizada.html","janela","status=no,width=500, heigth=100")
		janela()
	}
	</script>

	<div class="container">

	<!-- Include da navbar -->
	<?php include("../Includes/navbar-logado.html");?>

		<!-- Detalhe -->
		<div id="meuCarrinho" style="padding-top: 5%; padding-bottom: 5%">
			<?php include("/meu_carrinho_detalhes.php"); ?>
		
			<div>
				<table id="tableProdutos" class="table table-hover">
					<thead>
					  <tr>
						<th>Produto</th>
						<th>Quantidade</th>
						<th>Valor Unitário</th>
						<th>Valor Total</th>
					  </tr>
					</thead>
					<tbody>
					  <tr>
						<td>
							<!-- imagem -->
							<div id="imgCarrinho">
								<?php 
									$cod = $idProduto; 
								  	include("../includes/imagem.php");
								 	echo $imagem 
								 ?>
							</div> 
							<div id="txtImgCarrinho" style="padding-top:5%">
								<!-- nome produto -->
								<p style="text-align: left; vertical-align: middle;"> 
									<?php echo $produto; ?>
								</p>
							</div>
						</td>

						<!-- Quantidade --> 
						<td style="text-align: center; vertical-align: middle;">
							<?php echo $quantidade; ?>
							<!-- <input class="form-control" id="parc" type="number" min="0" max="12" step="1" value="2"> -->
						</td>
						<td style="text-align: center; vertical-align: middle;">

							<!-- Valor unitário -->
							<?php
								include("../Includes/conexao-banco.php");

								//query de insert
								$query = "SELECT vl_valor FROM produtos
								          WHERE idProduto = '$idProduto'";

								//executa a query
								if (mysqli_query($con, $query)) 
								{
									$resultado = mysqli_query($con, $query);
									
									//verifica se retornouo algum resultado
								    if(mysqli_num_rows($resultado) >= 1)
								    {
								    	//atribui o retorno da função para a variavel $preco
								    	$preco = mysqli_fetch_assoc($resultado);

								    	//divite o retorno(tira do array)
										$preco = implode(" ",$preco);

										echo "R$" . $preco;
								    }
								    else 
								    {
								   		echo "produto nao encontrado";
								   	}
								}
								else 
								    echo "Erro: " . $query . "<br>" . mysqli_error($con);
							?>
						</td>
						<!-- Valor total -->
						<?php
							include("../Includes/conexao-banco.php");

							//query 
							$query = "SELECT vl_valor FROM produtos
							          WHERE idProduto = '$idProduto'";

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
									$total = "R$" . $preco * $quantidade;
							    }
							    else 
							    {
							   		echo "produto nao encontrado";
							   	}
							}
							else 
							    echo "Erro: " . $query . "<br>" . mysqli_error($con);
						?>
						<td style="text-align: center; vertical-align: middle;"> <?php echo $total; ?> </td>
					  </tr>
					</tbody>
				</table>
				<div style="float:left; width:50%; font-weight: bold;"> 
					<p> <?php echo "Total: " . $total; ?> </p>

					<form method='GET' action="../Controller/valida-cupom.php">
						<div class="input-group" style="padding-top: 30px; padding-left: 20px;">
								<input type="text" class="form-control" name='cupom' placeholder="Cupom de Desconto">

							<span class="input-group-btn">
								<?php include("../controller/valida-cupom"); ?>
								<button class="btn btn-default" type="submit">Usar Cupom</button>
							</span>
						</div><!-- /input-group -->
					</form>
					
					<div class="form-group">
						<div class="col-xs-2">
							<p> <br/> </p>
							<button type="submit" class="btn btn-default btn-lgb" onclick=janela()>Comprar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Include do footer -->
	<?php include("../Includes/footer-fixo.html");?>


</body>
</html>


 
