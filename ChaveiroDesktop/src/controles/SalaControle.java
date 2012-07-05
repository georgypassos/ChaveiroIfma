package controles;

import dao.SalaDAO;

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
	
	
	
}
