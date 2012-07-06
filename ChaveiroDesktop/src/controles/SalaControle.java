package controles;

import java.util.List;

import dao.SalaDAO;
import entidade.Sala;
import excecoes.SistemaException;

public class SalaControle extends Controle {

	private SalaDAO salaDao = SalaDAO.getInstance();

	private static SalaControle salaControle;
	
	private SalaControle(){ }
	
	public static SalaControle getInstance(){
		
		if(salaControle == null){
			salaControle = new SalaControle();
		}
		
		return salaControle;
	}

	public List<Sala> consultaPorNome(String nome) throws SistemaException{
		
		List<Sala> list = salaDao.consultaPorNome(nome);
		
		if(list != null && list.size()>0){
			return list;
		}
		else{
			throw new SistemaException("Sem resultados!");
		}
		
	}
	
	
	
}
