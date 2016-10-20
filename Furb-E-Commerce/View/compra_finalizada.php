<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Cadastro Produtos</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<!-- imports -->
	<meta charset="utf-8">
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
		
		<?php 
			include("../includes/conexao-banco.php");
			
			$idPessoa = $_GET['idPessoa'];
			$idCarrinho = $_GET['idCarrinho'];
			$total = $_GET['total'];

			//chama procedure 
			$query = "CALL p_finalizaCompra('$idPessoa', '$idCarrinho', '$total')";

			//executa a query
			if (mysqli_query($con, $query)) 
			{
				?> 
				
				<div class="container" style="padding-top: 5%;">
					<p> Compra finalizada! </p>

					<a href="../index.php">
						<button class="btn btn-default"> 
							Ir para Home Page
						</button>
					</a>
				</div>
		
				<?php
			}
			else 
			{
			    echo "Erro: " . $query . "<br>" . mysqli_error($con);
			}

			//fecha a conexão
			mysqli_close($con);
		?>
		
		
	</div>
		



</body>
</html>


 
