package dao;

import entidade.Emprestimo;

public class EmprestimoDAO extends Dao<Emprestimo> {
	
	private static EmprestimoDAO emprestimoDAO;
	
	private EmprestimoDAO(){
		super(Emprestimo.class);
	}
	
	public static EmprestimoDAO getInstance(){
		
		if(emprestimoDAO == null){
			emprestimoDAO = new EmprestimoDAO();
		}
		
		return emprestimoDAO;
	}
	
	public Emprestimo salvar(Emprestimo e){
		
		return merge(e);
	}
	
}
