
<?php

	require "config.php";
	$query = "SELECT * FROM tbl_huongdan";
	$data = mysqli_query($connect, $query);

	class Cau1{

		function Cau1($huongdan){
			$this->huongdan  = $huongdan;
		}

	}

	$mangSV = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangSV, new Cau1($row['huongdan']));
	}

	echo json_encode($mangSV);

?>