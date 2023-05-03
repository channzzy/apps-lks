<?php
require('koneksi.php');

if($_SERVER['REQUEST_METHOD'] !== 'POST'){
    echo json_encode(['pesan' => "Invalid Request"]);
}

$nama = $_POST['nama_user'];
$alamat = $_POST['alamat'];
$telepon = $_POST['telepon'];
$username = $_POST['username'];
$password = md5($_POST['password']);
$fotoName = $_FILES['foto']['name'];
$fotoTmp = $_FILES['foto']['tmp_name'];
$fotoSize = $_FILES['foto']['size'];

$checkQuery = "select id from tbl_user where username ='$username'";
$ekse = mysqli_query($con, $checkQuery);
if(mysqli_num_rows($ekse) > 0){
    responseError("Username Sudah Digunakan", false);
}

if(empty($fotoName)){
    responseError("Silahkan Pilih Gambar", false);
}
else{
    $upload_path = 'foto_user/';
    $fileExt = strtolower(pathinfo($fotoName, PATHINFO_EXTENSION));
    $validExt = ['png', 'jpg', 'jpeg',];

    if(in_array($fileExt, $validExt)){

        if(!file_exists($upload_path . $fotoName)){

            if($fotoSize < 5000000){
                move_uploaded_file($fotoTmp, $upload_path . $fotoName);
            }else{
                responseError("Maaf Ukuran FIle Terlalu Besar", false);
            }
        }
            else{
                responseError("Maaf FIle Sudah Tersedia", false);
            }

    }
    else{
        responseError("Maaf Hanya Boleh JPEG,JPG,PNG", false);
    }
}
$sql = "insert into tbl_user(nama_user,alamat,telepon,username,password,foto) values('$nama','$alamat','$telepon','$username','$password', '$fotoName')";
$eksekusi = mysqli_query($con, $sql);
if(mysqli_affected_rows($con)){
    responseSuccess("Data Berhasil Disimpan", true);
}
