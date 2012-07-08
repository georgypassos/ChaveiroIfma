<?php

	include "conecta_db.inc";
	
	function getUltimaRetirada($id_sala){
	
		$query = "SELECT c.nome, e.dataRetirada AS dataAlteracao FROM Cliente c, Emprestimo e
		WHERE e.clienteRetirada = c.idcliente AND e.idemprestimo = (select max(idemprestimo)
		from Emprestimo where SALA = '" . mysql_real_escape_string($id_sala) . "' )";
		$resultado = mysql_query($query);
		$objeto = mysql_fetch_object($resultado);
		
		return $objeto;
	}
	
	function getUltimaEntrega($id_sala){
	
		$query = "SELECT c.nome, e.dataEntrega AS dataAlteracao FROM Cliente c, Emprestimo e
		WHERE e.clienteRetirada = c.idcliente AND e.idemprestimo = (select max(idemprestimo)
		from Emprestimo where SALA = '" . mysql_real_escape_string($id_sala) . "' )";
		$resultado = mysql_query($query);
		$objeto = mysql_fetch_object($resultado);
		
		return $objeto;
	}

?>