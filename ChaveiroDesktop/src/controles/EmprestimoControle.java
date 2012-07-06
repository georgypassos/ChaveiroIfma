package controles;

import dao.EmprestimoDAO;

public class EmprestimoControle extends Controle{
	
	private EmprestimoDAO emprestimoDao = EmprestimoDAO.getInstance();

	private static EmprestimoControle emprestimoControle;
	
	private EmprestimoControle(){ }
	
	public static EmprestimoControle getInstance(){
		
		if(emprestimoControle == null){
			emprestimoControle = new EmprestimoControle();
		}
		
		return emprestimoControle;
	}
	
	
	
	
}
