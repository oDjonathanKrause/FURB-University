<?PHP
session_start();
 
//Caso o usuário não esteja autenticado, limpa os dados e redireciona
if(!isset($_SESSION['login']) and !isset($_SESSION['senha'])) 
{
    //Destroi
	session_destroy();
 
    //Limpa
	unset($_SESSION['login']);
	unset($_SESSION['senha']);
	unset($_SESSION['ie_tipo']);
     
    //Redireciona para a página de autenticação
	header('location:index.php');
}


?>