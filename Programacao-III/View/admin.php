<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>E-Commerce Maneiro</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<!-- Imports -->
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
		
	<!-- Painel Administrativo -->
    <div id="admin" class="container">
        <h3 class="text-center" style="padding-top: 8%;">Painel Administrativo</h3> <br>
		
        <div>
            <div class="row" >
            	<div class="col-xs-1">
            		<button onclick="window.location='fornecedores.php';" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" style="width: 200px;">
				  		Cadastrar Fornecedor
					</button>
				</div>
			</div>
				
			<div class="row" style="padding-top: 1%;">
            	<div class="col-xs-1">
            		<button onclick="window.location='promocoes.php';" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" style="width: 200px;">
				  		Cadastrar Promoções
					</button>
				</div>
			</div>
			
			<div class="row" style="padding-top: 1%;">
            	<div class="col-xs-1">
            		<button onclick="window.location='listar-promocoes.php';" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" style="width: 200px;">
				  		Listar Promoções
					</button>
				</div>
			</div>

			<div class="row" style="padding-top: 1%;">
            	<div class="col-xs-1">
            		<button onclick="window.location='produtos.php';" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" style="width: 200px;">
				  		Cadastrar Produtos
					</button>
				</div>
			</div>

			<div class="row" style="padding-top: 1%;">
            	<div class="col-xs-1">
            		<button onclick="window.location='listar-produtos.php';" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" style="width: 200px;">
				  		Listar/Alterar Produtos
					</button>
				</div>
			</div>

			<div class="row" style="padding-top: 1%;">
            	<div class="col-xs-1">
            		<button onclick="window.location='lista-usuarios.php';" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" style="width: 200px;">
				  		Listar usuários
					</button>
				</div>
			</div>

			<div class="row" style="padding-top: 1%;">
            	<div class="col-xs-1">
            		<button onclick="window.location='graficos.php';" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" style="width: 200px;">
				  		Relatórios Gráficos
					</button>
				</div>
			</div>			
<!--
			<div class="row" style="padding-top: 1%;">
            	<div class="col-xs-1">
            		<button onclick="window.location='index.php';" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" style="width: 200px;">
				  		Estatísticas
					</button>
				</div>
			</div>

			<div class="row" style="padding-top: 1%;">
            	<div class="col-xs-1">
            		<button onclick="window.location='estoque.php';" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" style="width: 200px;">
				  		Estoque
					</button>
				</div>
			</div>
		-->
        	</div>
            
        </div>

    </div>
</div>
	

	<!-- Footer -->
	<footer class="footer" style="background-color: rgb(33,33,33); bottom: 0px; position: absolute;">
    	<div class="text-center">
    		<br>
        	<i id="facebook" class="fa fa-facebook" aria-hidden="true" ></i>
        	<i id="twitter" class="fa fa-twitter" aria-hidden="true"></i>
        	<i id="plus" class="fa fa-google-plus" aria-hidden="true"></i>
        	<br> <br>
    	</div>
	</footer><!--End footer 2-->

</body>
</html>


 
