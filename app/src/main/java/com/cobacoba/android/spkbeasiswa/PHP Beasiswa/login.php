<?php
$response = array();
if(isset($_POST['username']) && isset($_POST['password'])){
	$username = $_POST['username'];
	$password = $_POST['password'];
	$conn = mysqli_connect("localhost", "root", "", "spk_beasiswa");

	$sql = "SELECT * FROM users
			WHERE username = '$username'";

	$result = mysqli_query($conn, $sql);

	if($row = mysqli_fetch_array($result)){
		if(password_verify($password, $row['password'])){
			$response['success'] = 1;
		}else{
			$response['success'] = 0;
			$response['message'] = "Wrong Password";
		}
	}else{
		$response['success'] = 0;
		$response['message'] = "Username or Password doesn't match";
	}
}else{
	$response['success'] = 0;
	$response['message'] = "Please fill the empty form!";
}

echo json_encode($response);