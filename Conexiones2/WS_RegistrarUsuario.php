<?php

	$hostname_localhost="localhost";
	$database_localhost="goodjod";
	$username_localhost="root";
	$password_localhost="";

	$json=array();

	if (isset($_GET["Unombre"]) && isset($_GET["UPaterno"]) && isset($_GET["UMaterno"]) && isset($_GET["Udni"]) && isset($_GET["UfechaNacimiento"]) && isset($_GET["Ucelular"]) && isset($_GET["Ucorreo"]) && isset($_GET["Upass"]) && isset($_GET["UfechaRegistro"]) && isset($_GET["Uestado"])) {
		
		$Unombre=$_GET['Unombre'];
		$UPaterno=$_GET['UPaterno'];
		$UMaterno=$_GET['UMaterno'];
		$Udni=$_GET['Udni'];
		$UfechaNacimiento=$_GET['UfechaNacimiento'];
		$Ucelular=$_GET['Ucelular'];
		$Ucorreo=$_GET['Ucorreo'];
		$Upass=$_GET['Upass'];
		$UfechaRegistro=$_GET['UfechaRegistro'];
		$Uestado=$_GET['Uestado'];

		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$insert="INSERT INTO usuario(Unombre, UPaterno, UMaterno, Udni, UfechaNacimiento, Ucelular, Ucorreo, Upass, UfechaRegistro, Uestado, actividades_disponibles, publicaciones_disponibles, puntaje, reputacion_ptos, cantidad_votos) VALUES('{$Unombre}','{$UPaterno}','{$UMaterno}','{$Udni}','{$UfechaNacimiento}','{$Ucelular}','{$Ucorreo}','{$Upass}','{$UfechaRegistro}','{$Uestado}', 2, 0, 0, 0, 0)";

		$resultado_insert=mysqli_query($conexion,$insert);

		if ($resultado_insert) {
			$consulta="SELECT * FROM usuario WHERE Udni= '{$Udni}'";
			$resultado=mysqli_query($conexion,$consulta);

			if ($registro=mysqli_fetch_array($resultado)) {
				$json['usuario'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}else{
			$resulta["idUsuario"]=0;
			$resulta["UFoto"]=0;
			$resulta["Unombre"]='No Registra';
			$resulta["UPaterno"]='No Registra';
			$resulta["UMaterno"]='No Registra';
			$resulta["Udni"]=0;
			$resulta["UPasaporte"]=0;
			$resulta["UfechaNacimiento"]='No Registra';
			$resulta["Ucelular"]=0;
			$resulta["idPerfilP"]=0;
			$resulta["Ucorreo"]='No Registra';
			$resulta["idDistrito"]=0;
			$resulta["Udireccion"]='No Registra';
			$resulta["Upass"]='No Registra';
			$resulta["idTipoPremiun"]=0;
			$resulta["UfechaRegistro"]='No Registra';
			$resulta["Uestado"]=0;
			$json['usuario'][]=$resulta;
			echo json_encode($json);
		}
		

	}else{
			$resulta["idUsuario"]=0;
			$resulta["UFoto"]=0;
			$resulta["Unombre"]='WSNo Registra';
			$resulta["UPaterno"]='WS No Registra';
			$resulta["UMaterno"]='WS No Registra';
			$resulta["Udni"]=0;
			$resulta["UPasaporte"]=0;
			$resulta["UfechaNacimiento"]='WS No Registra';
			$resulta["Ucelular"]=0;
			$resulta["idPerfilP"]=0;
			$resulta["Ucorreo"]='WS No Registra';
			$resulta["idDistrito"]=0;
			$resulta["Udireccion"]='WS No Registra';
			$resulta["Upass"]='WS No Registra';
			$resulta["idTipoPremiun"]=0;
			$resulta["UfechaRegistro"]='WS No Registra';
			$resulta["Uestado"]=0;
			$json['usuario'][]=$resulta;
			echo json_encode($json);
		}

?>