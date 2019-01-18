<?php

$response = array();

if(isset($_POST['id_beasiswa'])){

	$koneksi = mysqli_connect("localhost", "root", "", "spk_beasiswa");

	$id = $_POST['id_beasiswa'];
	$sql = "UPDATE beasiswa
			SET flagactive = 'n'
			WHERE id_beasiswa = '$id'";
	$result = mysqli_query($koneksi, $sql);

	if(mysqli_affected_rows($koneksi) > 0){
		$response['success'] = 1;
		$response['message'] = "Beasiswa successfully deleted.";
	}else{
		$response['success']= 0;
		$response['message'] = "No beasiswa found.";
	}
}else{
	$response['success'] = 0;
	$response['message'] = "Required field(s) is missing!";
}

echo json_encode($response);