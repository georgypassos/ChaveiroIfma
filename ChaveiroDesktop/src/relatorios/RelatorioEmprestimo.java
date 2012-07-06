package relatorios;

import java.util.HashMap;
import java.util.List;

import entidade.Emprestimo;
import excecoes.SistemaException;

public class RelatorioEmprestimo extends Relatorio{
	
	private static final String fileJasper = "emprestimos.jasper";
	
	public static final int TIPO_CLIENTE = 0;
	public static final int TIPO_SALA = 1;
	
	public RelatorioEmprestimo() {
		super(fileJasper);
	}
	
	public void openPdf(List<Emprestimo> list, String nomeArquivo, int tipo) throws SistemaException {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("tipoRelatorio", tipo);
		
		openJasper(list, hashMap);
	}

}
