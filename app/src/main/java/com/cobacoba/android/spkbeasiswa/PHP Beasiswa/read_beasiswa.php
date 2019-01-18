<?php

//id_beasiswa, nim, penghasilan_orangtua, jumlah_tanggungan, ipk, skor, status
$response = array();

$koneksi = mysqli_connect("localhost", "root", "", "spk_beasiswa");
$sql = "SELECT mahasiswa.nim, mahasiswa.nama_mahasiswa, jurusan.nama_jurusan, beasiswa.penghasilan_orangtua, beasiswa.jumlah_tanggungan, beasiswa.ipk, beasiswa.skor, beasiswa.status FROM mahasiswa, beasiswa, jurusan WHERE mahasiswa.nim = beasiswa.nim AND jurusan.kode_jurusan = mahasiswa.kode_jurusan AND flagactive = 'y'";
$result = mysqli_query($koneksi, $sql);

if(mysqli_num_rows($result) > 0){
	$response['beasiswa'] = array();
	while($row = mysqli_fetch_array($result)){
		$beasiswa = array();
		$beasiswa['nim'] = $row['nim'];
		$beasiswa['nama_mahasiswa'] = $row['nama_mahasiswa'];
		$beasiswa['nama_jurusan'] = $row['nama_jurusan'];
		$beasiswa['penghasilan_orangtua'] = $row['penghasilan_orangtua'];
		$beasiswa['jumlah_tanggungan'] = $row['jumlah_tanggungan'];
		$beasiswa['ipk'] = $row['ipk'];
		$beasiswa['skor'] = $row['skor'];
		$beasiswa['status'] = $row['status'];
		array_push($response['beasiswa'], $beasiswa);
	}
	$response['success'] = 1;
}else{
	$response['success'] = 0;
	$response['message'] = "No beasiswa found.";
}

echo json_encode($response);