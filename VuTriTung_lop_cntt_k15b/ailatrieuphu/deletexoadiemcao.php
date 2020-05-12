<?php
	require "config.php";
	$idten = $_POST['idcuadiemcao'];
	$query = "DELETE FROM tbl_diem WHERE id = '$idten'";
	if(mysqli_query($connect, $query)){
		echo 'success';
	}else {
		echo 'error';
	}
?>