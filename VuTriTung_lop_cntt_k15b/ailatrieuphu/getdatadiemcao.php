
<?php

	require "config.php";
	$query = "SELECT * FROM tbl_diem";
	$data = mysqli_query($connect, $query);

	class DIEMCAO{
		function DIEMCAO($id, $ten, $tien){
			$this->id    = $id;
			$this->ten   = $ten;
			$this->tien  = $tien;
		}
	}

	$mangSV = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangSV, new DIEMCAO($row['id'], $row['ten'], $row['tien']));
	}

	echo json_encode($mangSV);

?>