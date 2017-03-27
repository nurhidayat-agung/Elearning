<?PHP 
    include_once("koneksi.php"); 
    if( isset($_POST['bab'])) { 
        $bab = $_POST['bab'];
        //$pass_user = md5($password);
        $query = "SELECT idMateri,bab FROM materi where bab like '%".$bab."%'"; 
        
        $result = mysqli_query($conn, $query);
  
        
        if($result->num_rows > 0){
            while ($data = mysqli_fetch_assoc($result)) {
            $data_array[] = $data;
        	}
        	echo json_encode($data_array);
            exit;
        } 
        else{ 
            echo "gagalcari"; 
            exit;
        } 
    } 
?>
<html>
<head><title>SearchSample</title></head>
    <body>
        <h1>Search</h1>
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            Username <input type="text" name="bab" value="" /><br/>
            <input type="submit" name="btnSubmit" value="cariMateri"/>
        </form>
    </body>
</html>