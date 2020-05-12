
<?php

	require "config.php";
	$query = "SELECT * FROM tbl_tien";
	$data = mysqli_query($connect, $query);

	class Tien{

		function Tien($id, $tien){
			$this->id = $id;
			$this->tien = $tien;		
		}

	}

	$mangSV = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangSV, new Tien($row['id'], $row['tien']));
	}

	echo json_encode($mangSV);

?>