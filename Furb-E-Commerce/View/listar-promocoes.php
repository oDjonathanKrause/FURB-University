<html>
<head>

	<title>Listar Promoções</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<!-- Imports -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-hQpvDQiCJaD2H465dQfA717v7lu5qHWtDbWNPvaTJ0ID5xnPUlVXnKzq7b8YUkbN" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<body>
	<?php include("../Includes/navbar-logado.html");?>
	<div class="container" style="padding-top: 75">

	<?php 

	//Conexão com o banco
	$con = mysqli_connect("127.0.0.1:3307", "root", "", "ecommercefurb");

	// Checa conexão
	if ($con->connect_error)
		die("Connection error: " . mysqli_connect_error());

	//query 
	$query = "SELECT * FROM promocoes
			  ORDER BY status desc";

	$result = mysqli_query($con, $query);

	//executa a query
	if (mysqli_query($con, $query)) 
	{
		while($row = mysqli_fetch_array($result))
		{ 
			$nome = $row['nome_promocao'];
			$desc = $row['desc_promocao'];
			$status = $row['status'];
			$perc = $row['qt_porcent'];
			$dt_i = $row['dt_inicio'];
			$dt_f = $row['dt_fim'];

			if($status === '1')
				$status = 'Ativa';
			else
				$status = 'Inativa';
			?>

			<div class='list-group' style='pading-top: 200px;'>
				<a class='list-group-item active'> Nome:  <?php echo $nome; ?> </a>
				<a class='list-group-item'> Descrição: <?php echo $desc; ?> </a>
				<a class='list-group-item'> Status: <?php echo $status; ?> </a>
				<a class='list-group-item'> Desconto: <?php echo $perc . '%'; ?> </a>
				<a class='list-group-item'> Data: <?php echo $dt_i . ' até ' . $dt_f; ?> </a>
			</div>
		<?php }
	}
	else 
	    echo "Erro: " . $query . "<br>" . mysqli_error($con);

	//fecha a conexão
	mysqli_close($con);

	?>

	</div> <!-- fim container -->



</body>
</html>