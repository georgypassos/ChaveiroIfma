package relatorios;

import java.util.HashMap;
import java.util.List;

import entidade.Emprestimo;

public class RelatorioEmprestimo extends Relatorio{
	
	private static final String fileJasper = "emprestimos.jasper";
	
	public RelatorioEmprestimo() {
		super(fileJasper);
	}
	
	public void openPdf(List<Emprestimo> list, String tipo) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("tipoRelatorio", tipo);
		
		//TODO colocar o nome da sala ou cliente no pdf
		
		openPdf(list, hashMap, true);
	}

}
