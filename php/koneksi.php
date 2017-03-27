<?php
	$servername = "localhost";
	$username = "root";
	$pass = "";
	$dbName = "eLearning";

	$conn = mysqli_connect($servername, $username, $pass, $dbName);

	if (!$conn) {
		die("connection failed " . mysqli_connect_error());
	}



?>