<?php

//nim, nama_mahasiswa, kode_jurusan, semester
$response = array();

$koneksi = mysqli_connect("localhost", "root", "", "spk_beasiswa");
$sql = "SELECT * FROM mahasiswa WHERE flagactive = 'y'";
$result = mysqli_query($koneksi, $sql);

if(mysqli_num_rows($result) > 0){
	$response['mahasiswa'] = array();
	while($row = mysqli_fetch_array($result)){
		$mahasiswa = array();
		$mahasiswa['nim'] = $row['nim'];
		$mahasiswa['nama_mahasiswa'] = $row['nama_mahasiswa'];
		$mahasiswa['kode_jurusan'] = $row['kode_jurusan'];
		$mahasiswa['semester'] = $row['semester'];
		array_push($response['mahasiswa'], $mahasiswa);
	}
	$response['success'] = 1;
}else{
	$response['success'] = 0;
	$response['message'] = "No mahasiswa found.";
}

echo json_encode($response);