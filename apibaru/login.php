<?php
require('koneksi.php');

if($_SERVER['REQUEST_METHOD'] !== 'POST'){
    echo json_encode(['Pesan' => "Invalid Request"]);
}

$username = $_POST['username'];
$password = md5($_POST['password']);

$sql = "select nama_user,alamat,telepon,foto,username from tbl_user where username='$username' and password='$password' limit 1";
$eksekusi = mysqli_query($con,$sql);
$data = mysqli_fetch_assoc($eksekusi);

if($data){
    echo json_encode([
        'kode' => 1,
        'data' => [
            "nama_user" => $data['nama_user'],
            "alamat" => $data['alamat'],
            "telepon" => $data['telepon'],
            "foto" => "http:10.0.2.2/apibaru/foto_user/" . $data['foto']
        ]
    ]);
}else{
    echo json_encode([
        'kode' => 0,
    ]);
}
mysqli_close($con);
