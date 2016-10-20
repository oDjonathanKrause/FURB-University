<?php

class Connection
{
	//seta as variaveis com as infos do banco
	var $server = "127.0.0.1:3307";
	var $user = "root";
	var $password = "";
	var $db = "ecommercefurb";
 
   //contrutor
   function __construct()
   {
      $this->Conectar();
   }
 

	function Conectar()
	{
		if(!($conectar = mysql_connect($this->server, $this->user, $this->password)))
		{
			echo "Erro ao tentar abrir a conexão!";
		} else
		{
			if(!($con = mysql_select_db($this->db, $conectar)))
			{
				echo "Erro ao selecionar o banco!";
			}
		}
	}
}