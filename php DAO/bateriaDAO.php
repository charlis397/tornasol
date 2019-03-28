<?php

$operacion=$_POST["operacion"];
$nombre=$_POST["nombre"];
$voltajeMax=$_POST["voltajeMax"]
$voltajeMin=$_POST["voltajeMin"]
$numeroCeldas=$_POST["numeroCeldas"]
$temperatura=$_POST["temperatura"]
$memoria=$_POST["memoria"]



$sql = "INSERT INTO bateria(idMecanismo,nombre,voltaje_max, voltaje_min, nu_celdas,temperatura_max,hasMemoria)
 values(1,".$nombre.",".$voltajeMax",".$voltajeMin",".$numeroCeldas",".$temperatura",".$memoria")";

?>