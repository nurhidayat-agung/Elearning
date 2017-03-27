<?PHP 
    include_once("koneksi.php"); 
    if( isset($_POST['idMateri'])) { 
        $idMateri = $_POST['idMateri'];
        //$pass_user = md5($password);
        $query = "SELECT * FROM materi where idMateri = ".$idMateri; 
        
        $result = mysqli_query($conn, $query);
  
        
        if($result->num_rows > 0){
            while ($data = mysqli_fetch_assoc($result)) {
            $data_array = $data;
        	}
        	echo json_encode($data_array);
            exit;
        } 
        else{ 
            echo "data not found"; 
            exit;
        } 
    } 
?>
<html>
<head><title>DetailSample</title></head>
    <body>
        <h1>Detil</h1>
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            idBarang <input type="text" name="idMateri" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Detil"/>
        </form>
    </body>
</html>