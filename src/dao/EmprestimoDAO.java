package dao;

public class EmprestimoDAO extends Dao {
	
	private static final EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
	
	public static EmprestimoDAO getInstance(){
		
		return emprestimoDAO;
	}
	
}
