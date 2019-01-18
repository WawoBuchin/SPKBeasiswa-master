<?php

$response = array();

if(isset($_POST['kode_jurusan']) && isset($_POST['nama_jurusan'])){

	$koneksi = mysqli_connect("localhost", "root", "", "spk_beasiswa");

	$kode = $_POST['kode_jurusan'];
	$nama = $_POST['nama_jurusan'];

	$sql = "INSERT INTO jurusan(kode_jurusan, nama_jurusan)
			VALUES('$kode', '$nama')";
	$result = mysqli_query($koneksi, $sql);

	if($result){
		$response['success'] = 1;
		$response['message'] = "Jurusan successfully created.";
	}else{
		$response['success'] = 0;
		$response['message'] = "Oops! An error occured!";
	}
}else{
	$response['success'] = 0;
	$response['message'] = "Required field(s) is missing!";
}

echo json_encode($response);