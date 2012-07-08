<?php
	$host = "localhost"; 
	$user = "root"; 
	$pass = "root"; 
	$base = "chaveiro"; 
	
	//Estabelecemos uma conexão com o banco de dados
	//mysql_connect("Nome ou IP do servidor", "Usuario", "Senha");
	$conn = mysql_connect($host, $user, $pass) or die("Impossivel conectar");
	
	//caso a conexão seja estabelecida corretamente seleciona o banco de dados a ser usado
	if($conn){
		$banco = mysql_select_db($base);
	}
	
?>



