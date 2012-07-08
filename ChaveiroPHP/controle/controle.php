<?php
include "./dao/salaDao.php";
include "./dao/emprestimoDao.php";


function getLastChange($status_sala, $id_sala){
	if ($status_sala == 0){
		$objeto = getUltimaEntrega($id_sala);
		return $objeto;
	}elseif($status_sala ==1){
		$objeto = getUltimaRetirada($id_sala);
		return $objeto;
	}else{
		$objeto = NULL;
		return $objeto;
	}
}

function getSala(){
	return getResultSala();
}


function ultimaAlteracao($id_sala, $status){
	$resultado = getLastChange($id_sala, $status);
	if ($resultado == NULL){
		// echo "<h1>PROBLEMA NO ACESSO AO BANCO DE DADOS</h1>";
		echo " <td> <label class='Alteracao'> - </label> </td>";
	}else{
		$cliente = $resultado->nome;
		$data = $resultado->dataAlteracao;
		$data = formatDate($data);
		echo " <td> <label class='Alteracao'>$data $cliente</label> </td>";
	}
}

function formatDate($data){
	
	return date("d/m H:i", strtotime($data));;
}

function printPorStatus($cada_linha){
	if ($cada_linha[1] == 0){
		echo " <tr bgcolor=\"#ECF8FD\">";
		echo " <td > <label> $cada_linha[0] </label> </td>";
		echo " <td class='Fechado'> <label> Fechada </label> </td>";
		ultimaAlteracao($cada_linha[1], $cada_linha[2]);
		echo " <td><a href=\"#\"><img src=\"./images/history.png\" alt=\"Ver hist&oacute;rico\" border=\"0\" /> </a></td>";
		echo " </tr>";
	}elseif ($cada_linha[1] == 1){
		echo " <tr bgcolor=\"#D9D9D9\">";
		echo " <td > <label> $cada_linha[0] </label> </td>";
		echo " <td class='Aberto'> <label> Aberta </label> </td>";
		ultimaAlteracao($cada_linha[1], $cada_linha[2]);
		echo " <td><a href=\"#\"><img src=\"./images/history.png\" alt=\"Ver hist&oacute;rico\" border=\"0\" /> </a></td>";
		echo " </tr>";
	}else{
		echo " <tr bgcolor=\"#FFBDBD\">";
		echo " <td > <label> $cada_linha[0] </label> </td>";
		echo " <td class='Indispo'> <label> Indispon&iacute;vel </label> </td>";
		echo " <td> <label class='Alteracao'> - </label> </td>";
		echo " <td><a href=\"#\"><img src=\"./images/history.png\" alt=\"Ver hist&oacute;rico\" border=\"0\" /> </a></td>";
		echo " </tr>";
	}

}

function printSalas(){
	$resultado = getSala();
		
	if ($resultado == null){
		echo "<h1>PROBLEMA NO ACESSO AO BANCO DE DADOS</h1>";
	}else {

		$linhas = mysql_num_rows ($resultado);
		for ($i=0 ; $i<$linhas ; $i++){
			$codigo_sala = mysql_result ($resultado , $i , "nome");
			$status = mysql_result ($resultado , $i , "status");
			$id_sala = mysql_result($resultado, $i, "idsala");
			$cada_linha = array($codigo_sala, $status, $id_sala);
			printPorStatus($cada_linha);

		}
	}
}

?>

