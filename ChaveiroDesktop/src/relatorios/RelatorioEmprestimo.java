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
	
	public void gerar(List<Emprestimo> list, String referencia, int tipo) throws SistemaException {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("tipoRelatorio", ""+tipo);
		hashMap.put("referencia", referencia);
		
		openJasper(list, hashMap);
	}

}
