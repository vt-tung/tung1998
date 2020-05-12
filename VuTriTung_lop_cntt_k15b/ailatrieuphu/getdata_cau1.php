
<?php

	require "config.php";
	$query = "SELECT * FROM tbl_cau1";
	$data = mysqli_query($connect, $query);

	class Cau1{

		function Cau1($noiDung, $dapAnDung, $dapAnSai1, $dapAnSai2, $dapAnSai3){
			$this->noiDung   = $noiDung;
			$this->dapAnDung = $dapAnDung;
			$this->dapAnSai1  = $dapAnSai1;
			$this->dapAnSai2  = $dapAnSai2;
			$this->dapAnSai3  = $dapAnSai3;
		}

	}

	$mangSV = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangSV, new Cau1($row['noiDung'], $row['dapAnDung'], $row['dapAnSai1'], $row['dapAnSai2'], $row['dapAnSai3']));
	}

	echo json_encode($mangSV);

?>