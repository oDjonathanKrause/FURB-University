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
		
	<!-- Contato -->
	<div align="center" style=" padding-bottom: 50px">
  
    <div id="contato" class="container">
        <h3 class="text-center" style="padding-top: 10%;">Contato</h3> <br>
        <div class="row test" style="width: 75%;">
            <div class="col-md-4" align="left">
                <p><span class="glyphicon glyphicon-map-marker"></span> Blumenau, SC</p>
                <p><span class="glyphicon glyphicon-phone"></span> 48 1516-2342</p>
                <p><span class="glyphicon glyphicon-envelope"></span> contato@ecommerceManeiro.com</p> 
            </div>
			
            <div class="col-md-8">
                <div class="row">
                    <div class="col-sm-6 form-group">
                        <input class="form-control" id="nome" name="nome" placeholder="Nome" type="text" required>
                    </div>
                    <div class="col-sm-6 form-group">
                        <input class="form-control" id="email" name="email" placeholder="Email" type="email" required>
                    </div>
                </div>
                <textarea class="form-control" id="comentario" name="comentario" placeholder="Mensagem" rows="5"></textarea>
				
                <div class="row">
                    <div class="col-md-12 form-group">
                        <br>
                        <button class="btn pull-right" type="submit">Enviar</button>
                    </div>
                </div> 
            </div>

            <!-- Maps -->
            <div id="map" class="col s6" style="width: 100%; height: 400px;"></div>       

        </div>
    </div>

	</div>
	
</div>
	<!-- Include do footer -->
	<?php include("../Includes/footer.html");?>
</body>

  <!-- Scripts -->
  <!-- Google Maps -->
  <script>
    function initMap() 
    {
      //Local: Teatro Carlos Gomes
      var myLatLng = {lat: -26.905638, lng: -49.079084};

      var map = new google.maps.Map(document.getElementById('map'), 
      {
        zoom: 14,
        center: myLatLng
      });

      var marker = new google.maps.Marker
      ({
        position: myLatLng,
        map: map,
        title: 'Estamos aqui!'
      });
    }
  </script>
  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCr6z7GSCGTe6moIY6d9OhM0gAWx_hDMoo&callback=initMap"> </script>


</html>


 
