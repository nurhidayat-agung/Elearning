<?php
    include_once("koneksi.php");
    if (isset($_POST['txtNamaGuru'])) {
        # code...
        $txtNamaGuru = $_POST['txtNamaGuru'];
        $txtEmailGuru = $_POST['txtEmailGuru'];
        $txtPassGuru = $_POST['txtPassGuru'];
        $txtPass_Guru =  md5($txtPassGuru);
        $txtMapelGuru = $_POST['txtMapelGuru'];

        $lalala = "INSERT INTO guru(namaGuru,emailGuru,passGuru,mataPelajaran) VALUES('$txtNamaGuru','$txtEmailGuru','$txtPass_Guru','$txtMapelGuru')";
        if (mysqli_query($conn,$lalala)) {
            echo "terdaftar";
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
        Nama Guru : <input type="text" name="txtNamaGuru" placeholder="silahkan masukan nama"  required /> <br />
        email Guru : <input type="email" name="txtEmailGuru" placeholder="masukan email" required /> <br />
        password : <input type="password" name="txtPassGuru" placeholder="masukan password" required /> <br />
        mata Pelajaran : <input type="text" name="txtMapelGuru" placeholder="mata pelajaran guru" required /> <br />
        <input type="submit" name="btnSubmit" value="Register Teacher">
    </form>
</body>
</html>