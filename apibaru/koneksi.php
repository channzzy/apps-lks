<?php
$con = mysqli_connect('localhost','root','','dbfood') or die ("koneksi gagal");

function responseError($pesan, $status){
    echo json_encode([
        'pesan' => $pesan,
        'status' => $status
    ]);
    exit();
}
function responseSuccess($pesan,$status){
    echo json_encode([
        'pesan'=> $pesan,
        'status' => $status
    ]);
    exit();
}
header('Content-type: appliaciton/json charset=utf-8');