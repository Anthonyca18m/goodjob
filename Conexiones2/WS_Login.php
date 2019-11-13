<?php

require_once("Config.php");

$json = array();

	if(isset($_GET["Ucorreo"]) && isset($_GET["Upass"])){
		
		$Ucorreo = htmlentities( $_GET['Ucorreo']);
		$Upass   = htmlentities( $_GET['Upass']);
		
		$conexion = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
		
		$queryUser="SELECT * FROM usuario WHERE Ucorreo = '{$Ucorreo}' AND Upass = '{$Upass}' AND estado = 1";
		$rsUser=mysqli_query($conexion,$queryUser);
		$countUser=mysqli_query($conexion,$queryUser);

		$queryCompany = "SELECT * FROM empresa WHERE Ecorreo = '{$Ucorreo}' AND Epass = '{$Upass}' AND estado = 1";

		$rsCompany = mysqli_query($conexion,$queryCompany);
		$countCompany = mysqli_query($conexion,$queryCompany);
		
		if(!empty(mysqli_fetch_row($countUser)))
		{
			if($rs = mysqli_fetch_array($rsUser)){
				$json['datos'][] = $rs ;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}else if(!empty(mysqli_fetch_row($countCompany)))
		{	
			if($rs = mysqli_fetch_array($rsCompany)){
				$json['datos'][] = $rs ;
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
