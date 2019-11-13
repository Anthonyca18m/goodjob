<?php

	require_once("Config.php");
	
	
    $id_usuario = $_GET['id_usuario'];
    $id_actividad = $_GET['id_actividad'];
	
    $consulta_string = "update postulacion_actividad set estado = 2 where 
    id_actividad = {$id_actividad} and id_usuario = {$id_usuario};";
	
	if (mysqli_query($con, $consulta_string))
	{
		echo 'Aceptado';
	}
	else{
		echo 'Ocurrió un error';
	}
	
	mysqli_close($con);
?>