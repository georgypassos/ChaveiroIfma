<?php

	// Recebemos os dados digitados pelo usu�rio
	$login = $_POST['login'];
	$senha = $_POST['senha'];
	
	//Estabelecemos uma conex�o com o banco de dados
	//mysql_connect("Nome ou IP do servidor", "Usuario", "Senha");
	$conn = mysql_connect("localhost", "root", "root") or die("Impossivel conectar");
	
	//caso a conex�o seja estabelecida corretamente seleciona o banco de dados a ser usado
	if($conn){
		mysql_select_db("chaveiro", $conn);
	}
	
	//Criamos o comando que efetua a busca do banco
	$sql = "SELECT idcliente, nome, perfil FROM cliente WHERE cpf = '$login' AND senha = '$senha'";
	
	//Executamos o comando
	$rs = mysql_query($sql, $conn);
	
	//Retornamos o numero de linhas afetadas
	$num = mysql_num_rows($rs);
	
	//Verificams se alguma linha foi afetada, caso sim retornamos suas informa��es
	if($num > 0){
		
		//Retorna os dados do banco
		$rst = mysql_fetch_array($rs);
		
		$perfil = $rst["perfil"];
		
		if($perfil == 1){
			$id = $rst["idcliente"];
			$nome = $rst["nome"];
			
			//Inicia a sess�o
			session_start();
			
			//Registra os dados do usu�rio na sess�o
			$_SESSION["id"]		= $id;
			$_SESSION["nome"]	= $nome;
			$_SESSION["login"]	= $login;
			
			//Encerra a conex�o com o banco
			mysql_close($conn);
			
			//Redireciona para o index
			header("Location:index.php");
		}
		else{
			$errorMessage = "Voc� n�o possui permiss�o de acesso"; //FIXME n�o esta exibindo a mensagem de erro na p�gina 
			header("Location:login.php");
		}
		
	}
	else {
		//Encerra a conex�o com o banco
		mysql_close($conn);
		//Caso nenhuma linha seja retornada emite o alerta e retorna
		$errorMessage = "Login ou senha invalidos."; //FIXME n�o esta exibindo a mensagem de erro na p�gina 
   		header("Location:login.php");
	}
?>