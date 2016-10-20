<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Estoque</title>
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
		
	<!-- Include da navbar -->
	<?php include("../Includes/navbar-logado.html");?>

		<!-- detalhes -->
		<div id="meuCarrinho" style="padding-top: 5%; padding-bottom: 5%">
		
			<div>
				<table id="tableProdutos" class="table table-hover">
					<thead>
					  <tr>
						<th>Produto</th>
						<th>Quantidade</th>
						<th>Valor</th>
						<th>Editar</th>
					  </tr>
					</thead>
					<tbody>
					  <tr>
						<td>
							<div id="imgCarrinho">
								<img src="..\imagens\canecaNasa.png" class="img-responsive"> 
							</div> 
							<div id="txtImgCarrinho" style="padding-top:5%">
								<p style="text-align: left; vertical-align: middle;"> Caneca Branca Nasa </p>
							</div>
						</td>
						<td style="text-align: center; vertical-align: middle;">
							<input class="form-control" id="parc" type="number" min="0" max="12" step="1" value="2">
						</td>
						<td style="text-align: center; vertical-align: middle;">R$ 19,90</td>
						<td style="text-align: center; vertical-align: middle;">
							<div class="form-group">
								<div class="col-xs-2">
									<button type="submit" class="btn btn-default" id="bt_sem_borda"><span class="glyphicon glyphicon-pencil"></button>
								</div>
							</div>
						</td> 
					  </tr>
					  <tr>
						<td>
							<div id="imgCarrinho">
								<img src="..\imagens\canecaDead.png" class="img-responsive"> 
							</div> 
							<div id="txtImgCarrinho" style="padding-top:5%">
								<p style="text-align: left; vertical-align: middle;"> Caneca Vermelha Deadpool! </p>
							</div>
						</td>
						<td style="text-align: center; vertical-align: middle;">
							<input class="form-control" id="parc" type="number" min="0" max="12" step="1" value="10">
						</td>
						<td style="text-align: center; vertical-align: middle;">R$ 19,90</td>
						<td style="text-align: center; vertical-align: middle;">
							<div class="form-group">
								<div class="col-xs-2">
									<button type="submit" class="btn btn-default" id="bt_sem_borda"><span class="glyphicon glyphicon-pencil"></button>
								</div>
							</div>
						</td> 
					  </tr>
					  <tr>
						<td>
							<div id="imgCarrinho">
								<img src="..\imagens\posterBatman.jpg" class="img-responsive"> 
							</div> 
							<div id="txtImgCarrinho" style="padding-top:10%">
								<p style="text-align: left; vertical-align: middle;"> Pôster Batman VS Superman Day VS Night </p>
							</div>
						</td>
						<td style="text-align: center; vertical-align: middle;">
							<input class="form-control" id="parc" type="number" min="0" max="12" step="1" value="7">
						</td>
						<td style="text-align: center; vertical-align: middle;">R$ 29,80</td>
						<td style="text-align: center; vertical-align: middle;">
							<div class="form-group">
								<div class="col-xs-2">
									<button type="submit" class="btn btn-default" id="bt_sem_borda">
										<span class="glyphicon glyphicon-pencil"></button>
								</div>
							</div>
						</td> 
					  </tr>
					</tbody>
				</table>
			</div>
			<div class="form-group" style="padding-bottom:5%; float:right;">
					<div class="col-xs-2">
						<p> <br/> </p>
						<button type="submit" class="btn btn-default">Submit</button>
					</div>
			</div>
		</div>
	</div>

	<!-- Include do footer -->
	<?php include("../Includes/footer.html");?>

</body>
</html>


 
