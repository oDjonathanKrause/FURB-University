
<?php

   //Starta sessão
   session_start(); 

   $login = $_POST['login'];
   $senha = $_POST['senha'];

   //faz conexão com o banco   
   $connection = mysql_connect("127.0.0.1:3307", "root", "") 
      or die(mysql_error());
   
   // seleciona db
   $db = mysql_select_db("ecommercefurb", $connection) 
      or die(mysql_error());
   
   // procura usuário
   $query = mysql_query("SELECT * FROM pessoa
                         WHERE senha = '$senha' 
                         AND (login = '$login' OR email = '$login')", $connection);

   $rows = mysql_num_rows($query);

   $pessoa = mysql_fetch_assoc($query);

   //se trazer uma linha, inicia sessão
   if ($rows !== 0) 
   {
      //Inicia sessão
      $_SESSION['login'] = $login;

      $_SESSION['ie_tipo'] = $pessoa['ie_tipo'];

      $_SESSION['idPessoa'] = $pessoa['idPessoa'];
      header("location: ../index.php"); 
   } 
   else 
   {
      //nao logou
      echo "Usuário ou senha inválidos";
   }
   
   //Fecha conexão 
   mysql_close($connection); 
?>