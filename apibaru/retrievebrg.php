<?php
require('koneksi.php');

$sql = "select * from tbl_barang";
$eksekusi = mysqli_query($con,$sql);

$barang = [];

if(mysqli_num_rows($eksekusi)>0){
    while($ambil = mysqli_fetch_object($eksekusi)){
        $ambil -> gambar = "http:10.0.2.2/apibaru/images/" . $ambil -> gambar;
        $ambil -> qty = 0;
        array_push($barang,$ambil);
    }
}
echo json_encode(['data' => $barang]);
mysqli_close($con);