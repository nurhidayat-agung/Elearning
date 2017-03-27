<?php
    include_once("koneksi.php");
    if (isset($_POST['mapel'])) {
        # code...
        $mapel = $_POST['mapel'];
        $bab = $_POST['bab'];
        $isiMateri = $_POST['isiMateri'];

        $lalala = "INSERT INTO materi(mapel,bab,isiMateri) VALUES('$mapel','$bab','$isiMateri')";
        if (mysqli_query($conn,$lalala)) {
            echo "sukses";
            exit;
        }else{
            echo "gagal";
            exit;
        }
    }
    
?>



<!DOCTYPE html>
<html>
<head>
    <title>eLeraning - register teacher</title>
</head>
<body>
    <h1>Pendaftaran Guru</h1>
    <form action="<?PHP $_PHP_SELF ?>" method="post">
        Nama Mata Pelajaran : <input type="text" name="mapel" placeholder="Masukan Nama Mata Pelajaran" required /> <br />
        Bab : <input type="text" name="bab" placeholder="masukan nama bab" required /> <br />
        mata Pelajaran : <textarea name="isiMateri" required placeholder="masukan isi Materi" rows="10" cols="100"></textarea> <br />

        <input type="submit" name="btnSubmit" value="Tambah Mapel">
    </form>
</body> 
</html>