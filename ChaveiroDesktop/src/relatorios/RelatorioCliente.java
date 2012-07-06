package relatorios;

import java.util.List;

import entidade.Cliente;

public class RelatorioCliente extends Relatorio{
	
	private static final String fileJasper = "relatorio_cliente.jasper";
	
	public RelatorioCliente() {
		super(fileJasper);
	}
	
	public void openPdf(List<Cliente> list) {
		openPdf(list, null, true);
	}

}
