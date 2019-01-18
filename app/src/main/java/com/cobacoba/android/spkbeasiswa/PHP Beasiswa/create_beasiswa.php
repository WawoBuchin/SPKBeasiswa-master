<?php
//id_beasiswa, nim, penghasilan_orangtua, jumlah_tanggungan, ipk, skor, status

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

	$sql = "INSERT INTO beasiswa(id_beasiswa, nim, penghasilan_orangtua, jumlah_tanggungan, ipk, skor, status)
			VALUES('$id', '$nim', '$penghasilan', '$tanggungan','$ipk', '$skor', '$status')";
	$result = mysqli_query($koneksi, $sql);

	if($result){
		$response['success'] = 1;
		$response['message'] = "Beasiswa successfully created.";
	}else{
		$response['success'] = 0;
		$response['message'] = "Oops! An error occured!";
	}
}else{
	$response['success'] = 0;
	$response['message'] = "Required field(s) is missing!";
}

echo json_encode($response);