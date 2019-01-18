<?php
$response = array();

$koneksi = mysqli_connect("localhost", "root", "", "spk_beasiswa");
$sql = "SELECT * FROM jurusan WHERE flagactive = 'y'";
$result = mysqli_query($koneksi, $sql);

if(mysqli_num_rows($result) > 0){
	$response['jurusan'] = array();
	while($row = mysqli_fetch_array($result)){
		$jurusan = array();
		$jurusan['kode_jurusan'] = $row['kode_jurusan'];
		$jurusan['nama_jurusan'] = $row['nama_jurusan'];
		array_push($response['jurusan'], $jurusan);
	}
	$response['success'] = 1;
}else{
	$response['success'] = 0;
	$response['message'] = "No jurusan found.";
}

echo json_encode($response);