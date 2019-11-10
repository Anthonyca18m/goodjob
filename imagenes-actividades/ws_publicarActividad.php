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
	
	$titulo = $_POST['titulo'];
	$descripcion = $_POST['descripcion'];
	$id_usuario = $_POST['id_usuario'];
	$fecha_fin = $_POST['fecha_fin'];
	$participantes_requeridos = $_POST['participantes_requeridos'];
	$recompensa = $_POST['recompensa'];
	$foto = $_POST['foto'];
	$url_foto = $id_usuario.$titulo;
	
	$consulta_string = "insert into actividad (id, titulo, descripcion, id_usuario, fecha_creacion, fecha_fin, 
	participantes_actuales, participantes_requeridos, foto, tipo_recompensa, recompensa, estado)  
	values(null, '{$titulo}', '{$descripcion}', {$id_usuario}, CURDATE(), '{$fecha_fin}', 0, {$participantes_requeridos}, 
	'{$url_foto}', 1, {$recompensa}, 1);";

	$ruta_subida = "imagenes-subidas/{$url_foto}.jpg";
	
	if (mysqli_query($con, $consulta_string))
	{
		file_put_contents($ruta_subida, base64_decode($foto));
		echo "Actividad Publicada";
	}
	else{
		echo mysqli_error($con);
	}
	
	mysqli_close($con);
?>
