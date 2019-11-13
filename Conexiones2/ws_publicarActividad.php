<?php

	define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_NAME', 'goodjod');
	
	$con = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	if(mysqli_connect_errno())
	{
		die('Unable to connect to database' . mysqli_connect_error());
	}
	
	$titulo = $_GET['titulo'];
	$descripcion = $_GET['descripcion'];
	$id_usuario = $_GET['id_usuario'];
	$fecha_fin = $_GET['fecha_fin'];
	$participantes_requeridos = $_GET['participantes_requeridos'];
	$recompensa = $_GET['recompensa'];
	
	$consulta_string = "insert into actividad (id, titulo, descripcion, id_usuario, fecha_creacion, fecha_fin, participantes_actuales, participantes_requeridos, foto, tipo_recompensa, recompensa, estado)  values(null, {$titulo}, {$descripcion}, {$id_usuario}, CURDATE(), {$fecha_fin}, 0, {$participantes_requeridos}, null, 1, {$recompensa}, 1);";
	
	if (mysqli_query($con, $consulta_string))
	{
		echo 'Actividad publicada!';
	}
	else{
		echo mysqli_error($con);
	}
	
	mysqli_close($con);
?>