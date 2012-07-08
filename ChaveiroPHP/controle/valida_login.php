<?php

	include "../dao/conecta_db.inc";
	
	$usuario = $_POST["cpf"];
	$senha = $_POST["senha"];
	
	$query = "SELECT * FROM Cliente WHERE cpf='" . mysql_real_escape_string($usuario) . "' AND senha='" . mysql_real_escape_string($senha) . "' AND perfil = 1 ";
	$resultado = mysql_query($query);
	$linhas = mysql_num_rows ($resultado);
	
	if($linhas<1){
		header("Location: ../login.html");
	}
	else{
		session_start();
	    $_SESSION['nome_usuario']=$usuario;
	    $_SESSION['senha_usuario']=$senha;
	    header("Location: ../chaveiro.php");
	}
	
	mysql_close($con);

	function fail_login(){
		// 
		//		Codigo para alertar quando o login falhar
		//
	}

?>