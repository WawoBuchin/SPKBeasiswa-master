<?php

$response = array();

if(isset($_POST['nim']) && isset($_POST['nama_mahasiswa'])&& isset($_POST['kode_jurusan'])&& isset($_POST['semester'])){

	$koneksi = mysqli_connect("localhost", "root", "", "spk_beasiswa");

	$kode = $_POST['kode_jurusan'];
	$nama = $_POST['nama_mahasiswa'];
	$nim = $_POST['nim'];
	$sms = $_POST['semester'];

	$sql = "INSERT INTO mahasiswa(nim, nama_mahasiswa, kode_jurusan, semester)
			VALUES('$nim', '$nama','$kode','$sms')";
	$result = mysqli_query($koneksi, $sql);

	if($result){
		$response['success'] = 1;
		$response['message'] = "Mahasiswa successfully created.";
	}else{
		$response['success'] = 0;
		$response['message'] = "Oops! An error occured!";
	}
}else{
	$response['success'] = 0;
	$response['message'] = "Required field(s) is missing!";
}

echo json_encode($response);