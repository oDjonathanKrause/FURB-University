<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title> Sistemas Seguros </title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
		<!-- Compiled and minified CSS -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">

		<!-- Compiled and minified JavaScript -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
	</head>
	
	<body>
		<div class="container">
			<h1> Sistemas Seguros </h1>

            <div class="col s12 m6">
              <div class="card blue-grey darken-1">
                <div class="card-content white-text">
                  <span class="card-title">Criptografia</span>
                  <p>Cifra de Cesar e Vigenere + tranposição</p>
                </div>

                <div class="card-action">
                  <a href="criptografia_display.php">Executar Criptografia</a>
                    
                    <form method="get" action="criptografia_display.php">
                        Texto: <input type="text" name="palavra"> <br>
                        Chave: <input type="text" name="chave"> <br>
                        <input type="submit">
                    </form>
                </div>
              </div>
            </div>
		</div>
	</body>
	
</html>