<html>
<head>

	<title>Listar Usuários</title>
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
		<h3>Tipos de usuário</h3>
			<table class="table">
			    <thead>
			      <tr>
			        <th>0/1</th>
			        <th>2</th>
			        <th>3</th>
			      </tr>
			    </thead>
			    <tbody>
			      <tr>
			        <td>Usuário Padrão</td>
			        <td>Fornecedor</td>
			        <td>Admin</td>
			      </tr>
			    </tbody>
			  </table>

	<?php 

	//Conexão com o banco
	$con = mysqli_connect("127.0.0.1:3307", "root", "", "ecommercefurb");

	// Checa conexão
	if ($con->connect_error)
		die("Connection error: " . mysqli_connect_error());

	//query 
	$query = "SELECT idPessoa, nm_pessoa, login, ie_tipo FROM pessoa
			  ORDER BY 1 desc";

	$result = mysqli_query($con, $query);

	//executa a query
	if (mysqli_query($con, $query)) 
	{
		while($row = mysqli_fetch_array($result))
		{ ?>
			<form method="POST" action="../controller/altera-tipo-usuario.php">
				<fieldset>
					<div class='list-group' style='pading-top: 200px;'>
						<a class='list-group-item active'> Nome: <?php echo $row['nm_pessoa'] ?> </a>
						<a class='list-group-item'> Login: <?php echo $row['login'] ?></a>
						<a class='list-group-item'> Tipo de usuário: <?php echo $row['ie_tipo'] ?> 

							<input name="ie_tipo" value="" type="number">
							<input name="idPessoa" value="<?php echo $row['idPessoa'] ?>" type="hidden">
							<button class="btn btn-alert" type="submit">Alterar</button></a>
					</div>
				</fieldset>
			</form>

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