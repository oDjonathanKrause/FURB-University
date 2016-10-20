<?php

//Dados retornadors da criar-conta.html
$login = $_POST['login'];
$senha = $_POST['senha'];
$email = $_POST['email'];
$nome  = $_POST['nome'];
$cpf   = $_POST['cpf'];

//Conexão com o banco
$servername = "127.6.220.130:3306";
$username = "adminc4zDcZa";
$password = "kXSIkCyB6mzM";
$dbname = "ecommercefurb";

try 
{
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    $sql = "INSERT INTO Pessoa (login, senha, email, nm_pessoa, cpf)
            VALUES ('$login','$senha', '$email', '$nome', '$cpf')";
    
    // use exec() because no results are returned
    $conn->exec($sql);
    echo "Usuário criado com sucesso!";

    header('Location: index.html'); //redireciona
}
catch(PDOException $e)
{
    echo $sql . "<br>" . $e->getMessage();
}

$conn = null;

?>
