<?php

	include "./dao/conecta_db.inc"; 
	
	session_start();
	 
	
	if(IsSet($_SESSION['nome_usuario']))
	   $nome_usuario=$_SESSION['nome_usuario'];
	if(IsSet($_SESSION['senha_usuario']))
	   $senha_usuario=$_SESSION['senha_usuario'];
	 
   if(!(empty($nome_usuario) OR empty($senha_usuario))){
      $query=mysql_query("SELECT * FROM Cliente WHERE cpf='" . mysql_real_escape_string($nome_usuario) . "'");
      $conta=mysql_num_rows($query);  
      if($conta>=1){
         if($senha_usuario != mysql_result($query,0,"senha")){
            unset($_SESSION['nome_usuario']);
            unset($_SESSION['senha_usuario']);
			header("Location: login.html");
            exit;
         }
      }
      else{
            unset($_SESSION['nome_usuario']);
            unset($_SESSION['senha_usuario']);
			header("Location: login.html");
            exit;
      }
   }
   else{
	   header("Location: login.html");
	   exit;
   }
 
	mysql_close($con);
    
?>