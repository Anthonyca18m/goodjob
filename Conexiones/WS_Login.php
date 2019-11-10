<?php

require_once("Config.php");


$json=array();

	if(isset($_GET["Ucorreo"]) && isset($_GET["Upass"])){
		
		$Ucorreo = htmlentities( $_GET['Ucorreo']);
		$Upass   = htmlentities( $_GET['Upass']);
		
		$conexion=mysqli_connect(DB_HOST, DB_USER, DB_PASS, DB_NAME);
		
		$consulta="SELECT * FROM usuario WHERE Ucorreo = '{$Ucorreo}' AND Upass = '{$Upass}'";
		$resultado=mysqli_query($conexion,$consulta);

		if($consulta){
		
			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}else{
			$results["Ucorreo"]='No encontrado';
			$results["Upass"]='No encontrado';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}else{
		   	$results["Ucorreo"]='WS Registro no encontrado!';
			$results["Upass"]='WS Registro no encontrado!';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
?>