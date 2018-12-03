<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>E-Commerce Maneiro</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<!-- imports -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-hQpvDQiCJaD2H465dQfA717v7lu5qHWtDbWNPvaTJ0ID5xnPUlVXnKzq7b8YUkbN" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>

	<script>
	</script>

	<div class="container">
		
		<?php //Include da navbar 
        	include("../Includes/navbar-logado.html");

			//conecta
			$mysql = mysql_connect('127.0.0.1:3307', 'root', '');
			mysql_select_db('ecommercefurb', $mysql);

			$idProduto = $_GET['idProduto'];
			//$idProduto = 1;

			//chama procedure
			$rs = mysql_query("CALL obterDadosProduto('$idProduto', @nm_produto, @vl_valor, @ds_produto, @parcelas)");

			//joga resultado no array
			$resultado2 = mysql_query("select @idProduto, @nm_produto, @vl_valor, @ds_produto, @parcelas;");
			$Produto = mysql_fetch_assoc($resultado2);

			//calcula valor das parcelas
			$parcelas = $Produto['@parcelas'];
			$valor = $Produto['@vl_valor'];

			if($parcelas === 0)
				$vl_parcelas = 1;
			else
				$vl_parcelas = $valor / $parcelas;

			//calcula quantidade parcelas
			$qtd_parcelas = $valor / $vl_parcelas;

			session_start();
		?>

		
		
		<!-- detalhes -->
		<form method="POST" action="../Controller/adicionar_carrinho.php">
		<fieldset>
		<div id="detalheProduto" style="padding-top: 5%; padding-bottom: 5%">
			<div class="container text-center" id="layoutDiv">
			
				<div id="imgProduto">
					<?php 
						$cod = $idProduto;
						include('../Includes/imagem.php'); 
					?>
					<!-- <img src="..\imagens\canecaNasa.png" class="img-responsive"> -->
					
					<div id="itemDestaque"  class="item-image">
						<?php echo $imagem; ?>
	        		</div>
				</div>
			
				<div id="detalhes">
					<div style="margin-top:10%; margin-left:10%; margin-rigth:10%;">
						<!-- Nome produto -->
						<h2> <?php echo $Produto['@nm_produto'] ?> </h2>
						
						<br/>

						<!-- Descrição -->
						<p> <?php echo $Produto['@ds_produto'];?> </p>				
						
						<div>
							<!-- Preco -->
							<div id="preco"><span class="fa fa-usd"> Por: R$ <?php echo $Produto['@vl_valor']; ?> </span></div>
							

							<!-- Parcelas -->
							<div> <?php echo "Ou " . $qtd_parcelas . "x de R$" . $vl_parcelas; ?></div>
						</div>
						<form role="form">
							<div class="form-group">
								<div class="col-xs-2">
									<p> <br/> </p>
									<button type="submit" class="btn btn-default btn-lgb">
										Adicionar ao carrinho
										<span class="glyphicon glyphicon-shopping-cart"></span>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- Parametros enviados por POST para add-carrinho.php -->
		<input name="idProduto" value="<?php echo $idProduto; ?>" type="hidden">
		<input name="nm_produto" value="<?php echo $Produto['@nm_produto']; ?>" type="hidden">
		<input name="qtd_estoque" value="<?php echo $Produto['@qtd_estoque']; ?>" type="hidden">
		<input name="vl_valor" value="<?php echo $Produto['@vl_valor']; ?>" type="hidden">
		<input name="ds_produto" value="<?php echo $Produto['@ds_produto']; ?>" type="hidden">
	</fieldset>
	</form>
		
   
	</div>
	
	<!-- Include do footer -->
	<?php include("../Includes/footer-fixo.html");?>


</body>
</html>


 
