<?php

$response = array();

if(isset($_POST['nim'])){

	$koneksi = mysqli_connect("localhost", "root", "", "spk_beasiswa");

	$nim = $_POST['nim'];
	$sql = "UPDATE mahasiswa
			SET flagactive = 'n'
			WHERE nim = '$nim'";
	$result = mysqli_query($koneksi, $sql);

	if(mysqli_affected_rows($koneksi) > 0){
		$response['success'] = 1;
		$response['message'] = "Mahasiswa successfully deleted.";
	}else{
		$response['success']= 0;
		$response['message'] = "No mahasiswa found.";
	}
}else{
	$response['success'] = 0;
	$response['message'] = "Required field(s) is missing!";
}

echo json_encode($response);