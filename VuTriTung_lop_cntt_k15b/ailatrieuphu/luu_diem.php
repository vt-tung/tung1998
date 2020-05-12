<?php
	require "config.php";
	$tenNguoiChoi  = $_POST['tenNguoiChoi'];
	$tien          = $_POST['tien'];
	$query         = "INSERT INTO tbl_diem VALUES(null, '$tenNguoiChoi', '$tien')";
	if(mysqli_query($connect, $query)){
		echo 'success';
	}else {
		echo 'error';
	}

?>