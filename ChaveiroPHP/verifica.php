<?php
	//Inicia a sessão
	session_start();
	
	//Verifica se há dados ativos na sessão
	if(empty($_SESSION["id"]) || empty($_SESSION["nome"]) || empty($_SESSION["login"])){
		//Caso não exista dados registrados, exige login
		header("Location:login.php");
	}
?>