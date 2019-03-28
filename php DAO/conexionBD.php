<?php
	$servidorBD = "localhost";
	$usrBD = "root";
	$passBD = "";
	$nombreBD = "biblioteca";
	
	$conexion = mysqli_connect($servidorBD, $usrBD, $passBD, $nombreBD);
	mysqli_query($conexion,"SET NAMES 'utf8'");
?>