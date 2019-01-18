<?php

$response = array();

if(isset($_POST['id_beasiswa']) && isset($_POST['nim'])&& isset($_POST['penghasilan_orangtua'])&& isset($_POST['jumlah_tanggungan']), && isset($_POST['ipk'])&& isset($_POST['skor']), && isset($_POST['status'])){

	$koneksi = mysqli_connect("localhost", "root", "", "spk_beasiswa");

	$id = $_POST['id_beasiswa'];
	$nim = $_POST['nim'];
	$penghasilan = $_POST['penghasilan_orangtua'];
	$tanggungan = $_POST['jumlah_tanggungan'];
	$ipk = $_POST['ipk'];
	$skor = $_POST['skor'];
	$status = $_POST['status'];

	$sql = "UPDATE beasiswa
			SET nim = '$nim', penghasilan_orangtua = '$penghasilan', jumlah_tanggungan = '$tanggungan', ipk = '$ipk', skor = '$skor', status = '$status'
			WHERE id_beasiswa = '$id'";
	$result = mysqli_query($koneksi, $sql);

	if(mysqli_affected_rows($koneksi) > 0){
		$response['success'] = 1;
		$response['message'] = "Beasiswa successfully updated.";
	}else{
		$response['success']= 0;
		$response['message'] = "No beasiswa found.";
	}
}else{
	$response['success'] = 0;
	$response['message'] = "Required field(s) is missing!";
}

echo json_encode($response);