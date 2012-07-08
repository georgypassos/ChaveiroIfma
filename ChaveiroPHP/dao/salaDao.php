<?php

	include "conecta_db.inc";
	
	function getResultSala(){
		$query = "select * from sala";
		$resultado = mysql_query($query);
		
		return $resultado;
	}
	
	function getObjetoSala(){
		$query = "select * from sala";
		$resultado = mysql_query($query);
		$objeto = mysql_fetch_object($resultado);
		
		return $objeto;
	}
	
	function getArraySala(){
		$query = "select * from sala";
		$resultado = mysql_query($query);
		$linha = mysql_fetch_array($resultado);
		
		return $linha;
	}
	
	function getAssocSala(){
		$query = "select * from sala";
		$resultado = mysql_query($query);
		$assoc = mysql_fetch_assoc($resultado);
		
		return $assoc;
	}


?>