<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>E-Commerce Maneiro</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-hQpvDQiCJaD2H465dQfA717v7lu5qHWtDbWNPvaTJ0ID5xnPUlVXnKzq7b8YUkbN" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
	<div class="container">
		<?php 
			$servername = "127.0.0.1:3307";
			$username = "root";
			$password = "";
			$dbname = "ecommercefurb";


			$con = mysqli_connect($server, $user, $password, $db);
			// Check connection
			if (!$con)
		  		die("Connection error: " . mysqli_connect_error());

		  	var_dump("09");

/*
			echo '<h1>Connected to FUCKING MySQL</h1>';

		    $sql = "SELECT id, text, generic1 FROM t_test";
			$result = $con->query($sql);

			if ($result->num_rows > 0) 
			{
			    // output data of each row
			    while($row = $result->fetch_assoc()) 
			    {
			        echo "id: " . $row["id"]. 
			        	 " - text: " . $row["text"]. 
			        	 " - generic: " . $row["generic"] . "<br>";
			    }
			} 
			else 
			{
			    echo "0 results";
			}

			$con->close();*/
		?>
	</div>
</body>
</html>