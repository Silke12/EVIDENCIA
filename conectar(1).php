<?php
	
	$usu = $_REQUEST['usu'];
	$pass = $_REQUEST['pass'];
	$conexion = new mysqli('localhost','root','','agenda');
	$error = $conexion->connect_errno;
	if ($error!=null){
		echo "ERROR";
		exit();
	}else{
		$consulta = "SELECT * FROM usuarios where nombre= '$usu' and pass ='$pass'";
		//echo $consulta."<br>";
		$resultado = $conexion->query($consulta);
		$datos = array();
		foreach ($resultado as $row){
			$datos = $row;
		}
		echo json_encode($datos);		
	}

?>