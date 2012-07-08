<?php
	//Inclui o arquivo de verificação
	include_once("verifica.php");
	
	include "controle/controle.php";
	
	ini_set('default_charset','ISO-8859-1');
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Chaveiro do IFMA</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script type="text/javascript" src="clockp.js"></script>
<script type="text/javascript" src="clockh.js"></script>
<script type="text/javascript" src="jquery.min.js"></script>
<script type="text/javascript" src="ddaccordion.js"></script>
<script type="text/javascript">
	ddaccordion.init({
		headerclass: "submenuheader", //Shared CSS class name of headers group
		contentclass: "submenu", //Shared CSS class name of contents group
		revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
		mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
		collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
		defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
		onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
		animatedefault: false, //Should contents open by default be animated into view?
		persiststate: true, //persist state of opened contents within browser session?
		toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
		togglehtml: ["suffix", "<img src='images/plus.gif' class='statusicon' />", "<img src='images/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
		animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
		oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
			//do nothing
		},
		onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
			//do nothing
		}
	})
	
</script>

<script type="text/javascript" src="jconfirmaction.jquery.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		$('.ask').jConfirmAction();
	});
	
</script>

<script language="javascript" type="text/javascript" src="niceforms.js"></script>
<link rel="stylesheet" type="text/css" media="all"
	href="niceforms-default.css" />

</head>
<body>
	<div id="main_container">

		<div class="header">
			<div class="logo">
				<a href="#"><img src="images/logo.png" alt="" title="" border="0" />          CHAVEIRO DO IFMA</a>
			</div>

			<div class="right_header">
				<a href="#" class="messages">(0) Mensagens</a> | <?php echo $_SESSION['nome'] ?> <a href="destroy.php" class="logout">(Sair)</a>
			</div>
			<div id="clock_a"></div>
		</div>

		<div class="main_content">

			<div class="menu">
				<ul>
					<li><a class="current" href="index.php">In&iacute;cio</a></li>
					<li><a href="">Gerenciar<!--[if IE 7]><!-->
					</a> <!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
						<ul>
							<li><a href="#" title="">Usu&aacute;rios</a></li>
							<li><a href="#" title="">Salas</a></li>
						</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
					</li>
					<li><a href="#">Empr&eacute;stimos</a></li>
					<li><a href="#">Relat&oacute;rios</a></li>
					<li><a href="#">Contato</a></li>
				</ul>
			</div>

			<div class="center_content">

				<div class="right_content">

					<h2>Situa&ccedil;&atilde;o das salas</h2>

					<table id="rounded-corner" summary="2007 Major IT Companies' Profit" width="100%" align="center">
						<thead>
							<tr>
								<th scope="col" class="rounded-lft">Sala</th>
								<th scope="col" class="rounded">Status</th>
								<th scope="col" class="rounded">&Uacute;ltima altera&ccedil;&atilde;o</th>
								<th scope="col" class="rounded-rht">Hist&oacute;rico</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="3" class="rounded-foot-left">
									<em>Informa&ccedil;&otilde;es adicionais aqui.</em>
								</td>
								<td class="rounded-foot-right">&nbsp;</td>
							</tr>
						</tfoot>
						<tbody >
							<? printSalas(); ?>
						</tbody>
					</table>

					<div class="pagination">
						<span class="disabled"><< prev</span><span class="current">1</span><a
							href="">2</a><a href="">3</a><a href="">4</a><a href="">5</a>..¦<a
							href="">10</a><a href="">11</a><a href="">12</a>...<a href="">100</a><a
							href="">101</a><a href="">next >></a>
					</div>

				</div>
				<!-- end of right content-->

			</div>
			<!--end of center content -->

			<div class="clear"></div>
		</div>
		<!--end of main content-->


		<div class="footer">

			<div class="left_footer_login">
				<br></br>&copy; Copyright by Georgy Passos, Leonardo Xavier, Tayn&aacute; Gon&ccedil;alves (Acad&ecirc;micos de Sistemas de Informa&ccedil;&atilde;o - IFMA)
				<br></br>Todos os direitos reservados
			</div>
			<div class="right_footer">
				<a href="http://indeziner.com"><img src="images/indeziner_logo.gif"
					alt="" title="" border="0" /> </a>
			</div>

		</div>

	</div>
</body>
</html>
