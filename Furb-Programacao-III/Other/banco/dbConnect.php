<?php  
    class dbConnect 
    {  
        function __construct() 
        {  
            require_once('config.php');  
            
            $conn = mysql_connect(host, user, password);  
            mysql_select_db(database, $conn);  

            if(!$conn)// testing the connection  
                die ("Cannot connect to the database" . mysqli_connect_error());  

            return $conn;  
        }  
        public function Close()
        {  
            mysql_close();  
        }  
    }  
?> 