
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

<body>
	<?php include("../Includes/navbar-logado.html");?>
	<div class="container">
		
		<!-- Carousel -->
		<div  style="padding-top: 4%;" id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
			
			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="imagens\carrousel2.jpg">
				</div>
			
				<div class="item">
					<img src="imagens\carrousel1.jpg">
				</div>
			
				<div class="item">
					<img src="imagens\carrousel3.jpg">
				</div>
			</div>
			
			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Anterior</span>
			</a>
			<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Próximo</span>
			</a>
		</div>
	
		<div id="divisor1" style="border: 2px solid; height: 20%;"></div>

	<!-- Destaques -->
    <?php include("/Includes/destaques-index.php");?>

	</div>
	<footer class="footer" style="background-color: rgb(33,33,33);">
    	<div class="container text-center">
    		<br>
        	<i id="facebook" class="fa fa-facebook" aria-hidden="true" ></i>
        	<i id="twitter" class="fa fa-twitter" aria-hidden="true"></i>
        	<i id="plus" class="fa fa-google-plus" aria-hidden="true"></i>
        	<br> <br>
    	</div><!--End container-->
	</footer><!--End footer 2-->


</body>
</html>


 
