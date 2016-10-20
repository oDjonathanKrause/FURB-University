<?php  

require_once 'dbConnect.php';  
session_start();  
    class dbFunction 
    {          
        function __construct() 
        {        
            // connecting to database  
            $db = new dbConnect();
        }  

        // destructor  
        function __destruct() 
        {         }  
        
        public function Cadastro($login, $email, $senha)
        {  
                $senha = md5($senha);  
                $query = mysql_query("INSERT INTO pessoa(login, email, senha) 
                					  values('".$login."','".$email."','".$senha."')") 
                					  or die(mysql_error());  
                return $query;  
        }  

        public function Login($email, $senha)
        {  
            $res = mysql_query("SELECT * FROM pessoa WHERE login = '".$login."' AND senha = '".md5($senha)."'");  
            $user_data = mysql_fetch_array($res);  
            $no_rows = mysql_num_rows($res);  
              
            if ($no_rows == 1)   
            {  
                $_SESSION['login'] = true;  
                $_SESSION['id'] = $user_data['id'];  
                $_SESSION['login'] = $user_data['login'];  
                $_SESSION['email'] = $user_data['email'];  
                return TRUE;  
            }  
            else  
            {  
                return FALSE;  
            }  
        }  

        public function UsuarioJaRegistrado($email)
        {  
            $query = mysql_query("SELECT * FROM pessoa WHERE email = '".$email."'");  
            echo $row = mysql_num_rows($query);  
            
            if($row > 0)
            {  
                return true;  
            } 
            else 
            {  
                return false;  
            }  
        }  
    }  
?>  