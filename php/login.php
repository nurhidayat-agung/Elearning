<?php
	include_once("koneksi.php");


	if (isset($_POST['txtUsername'])) {
		# code...
		$user = $_POST['txtUsername'];
		$pass = $_POST['txtPassword'];
		$Password = md5($pass);

		$query = "SELECT namaGuru from guru where namaGuru = '$user' AND passGuru = '$Password'";
		$result =  array();

		$result = mysqli_query($conn, $query);
		
		if($result->num_rows > 0){
            while ($data = mysqli_fetch_assoc($result)) {
            	$data_array = $data;
        	}
        	echo json_encode($data_array);
            exit;
        } 
        else{ 
            echo "login failed"; 
            exit;
        } 

	}

?>





<!DOCTYPE html>
<html>
<head>
	<title>Login Guru</title>
</head>
<body>
	<h1>Halaman Login Guru</h1>
	<form action="<?PHP $_PHP_SELF ?>" method="post">
		Username : <input type="text" name="txtUsername" value="" placeholder="masukan Username" required /> <br />
		Password : <input type="Password" name="txtPassword" placeholder="masukan Password" required  value="" /> <br />
		<input type="submit" name="btnSubmit" value="Login">
	</form>

</body>
</html>