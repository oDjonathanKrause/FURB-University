<html>
	<head>
		<title> Sistemas Seguros </title>
	
		  <!-- Compiled and minified CSS -->
		  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">

		  <!-- Compiled and minified JavaScript -->
		  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
	</head>
	
	<body>
		<div class="container">
			<h2> Criptografia </h2>
			<h4> Encripta </h4>
			<?php
				require_once("Criptografia.class.php");

                //Instância classe de Criptografia
				$criptografia = new Criptografia();
            
                $palavra_global = strtoupper($_GET['palavra']);
                $chave_cesar = 2;
                $chave_vigenere = strtoupper($_GET['chave']);

			    //Chama Cesar	
				$result_cesar = $criptografia->encript_cesar($palavra_global, $chave_cesar);
			    echo "Cesar: <br>";	
                echo $result_cesar;
                
                //Chama Vigenere
                echo "<br><br> Vegenere: <br>";    
                $result_vigenere = $criptografia->encript_vigenere($palavra_global, $chave_vigenere); 
                echo $result_vigenere;
            
                //Chama Transposição
                echo "<br><br> Transposição: <br>";
                $result_transposicao = $criptografia->encript_transposicao($palavra_global);    
                
			?>
            
            <h4> Desencripta </h4>
            <?php
                //Desencripta Cesar 
                $result_desencript_cesar = $criptografia->desencript_cesar($result_cesar, $chave_cesar);    
                echo "Desencripta Cesar: <br>";
                echo $result_desencript_cesar;
            
                //Desencripta Transposição
                $result_desencript_transposicao = $criptografia->desencript_transposicao($result_transposicao);    
                echo "<br><br>Desencripta Transposição: <br>";
                echo $result_desencript_transposicao;
            
                //Desencript Vigenere
                $result_desencript_vegenere = $criptografia->desencript_vigenere($result_vigenere, $chave_vigenere);
                echo "<br><br>Desencripta Vegenere: <br>";
                echo $result_desencript_vegenere;        
            ?>
            
            <h4> Encripta Cesar + Vigenere + Transposição </h4>
            <?php
                //Encripta Cesar + Vigenere
                $result_cesar_vigenere = $criptografia->encript_vigenere($criptografia->encript_cesar($palavra_global, $chave_cesar), $chave_vigenere);
            
                //Usa o resultado de Cesar + Vigenere pra gerar a transposição
                $result_cesar_vigenere_transposicao = $criptografia->encript_transposicao($result_cesar);
            ?>
            
            <h4> Desencripta Cesar + Vigenere + Transposição </h4>
            <?php
                //Desencripta Transposicação
                $result_desencript_transposicao = $criptografia->desencript_transposicao($result_cesar_vigenere_transposicao);
            
                //Desencripta Vigenere
                $result_desencript_vigenere = $criptografia->desencript_vigenere($result_desencript_transposicao, $chave_vigenere);
            
                //Desencripta Cesar
                $result_desencript_cesar = $criptografia->desencript_cesar($result_desencript_transposicao, $chave_cesar);    
            
                echo $result_desencript_cesar . "<br><br>";
            
            ?>
            
            
			
		</div>
	 
	</body>
	
</html>