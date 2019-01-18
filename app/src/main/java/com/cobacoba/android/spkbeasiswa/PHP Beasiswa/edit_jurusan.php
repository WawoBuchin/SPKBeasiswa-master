<?php

$response = array();

if(isset($_POST['kode_jurusan']) && isset($_POST['nama_jurusan'])){

	$koneksi = mysqli_connect("localhost", "root", "", "spk_beasiswa");

	$kode = $_POST['kode_jurusan'];
	$nama = $_POST['nama_jurusan'];
	$sql = "UPDATE jurusan
			SET nama_jurusan = '$nama'
			WHERE kode_jurusan = '$kode'";
	$result = mysqli_query($koneksi, $sql);

	if(mysqli_affected_rows($koneksi) > 0){
		$response['success'] = 1;
		$response['message'] = "Jurusan successfully updated.";
	}else{
		$response['success']= 0;
		$response['message'] = "No jurusan found.";
	}
}else{
	$response['success'] = 0;
	$response['message'] = "Required field(s) is missing!";
}

echo json_encode($response);