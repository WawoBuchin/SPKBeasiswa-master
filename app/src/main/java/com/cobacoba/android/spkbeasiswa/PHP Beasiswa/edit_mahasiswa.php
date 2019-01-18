<?php

$response = array();

if(isset($_POST['nim']) && isset($_POST['nama_mahasiswa'])&& isset($_POST['kode_jurusan'])&& isset($_POST['semester'])){

	$koneksi = mysqli_connect("localhost", "root", "", "spk_beasiswa");
	$kode = $_POST['kode_jurusan'];
	$nama = $_POST['nama_mahasiswa'];
	$nim = $_POST['nim'];
	$sms = $_POST['semester'];

	$sql = "UPDATE mahasiswa
			SET nama_mahasiswa = '$nama', kode_jurusan = '$kode', semester = '$sms'
			WHERE nim = '$nim'";
	$result = mysqli_query($koneksi, $sql);

	if(mysqli_affected_rows($koneksi) > 0){
		$response['success'] = 1;
		$response['message'] = "Mahasiswa successfully updated.";
	}else{
		$response['success']= 0;
		$response['message'] = "No jurusan found.";
	}
}else{
	$response['success'] = 0;
	$response['message'] = "Required field(s) is missing!";
}

echo json_encode($response);