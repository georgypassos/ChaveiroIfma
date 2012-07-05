package dao;

import entidade.Emprestimo;

public class EmprestimoDAO extends Dao<Emprestimo> {
	
	private static final EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
	
	private EmprestimoDAO(){
		super(Emprestimo.class);
	}
	
	public static EmprestimoDAO getInstance(){
		
		return emprestimoDAO;
	}
	
	public void inserir(Emprestimo e){
		
		merge(e);
		
	}
	
	public void atualizar(Emprestimo e){
		
		merge(e);
		
	}
	
}
