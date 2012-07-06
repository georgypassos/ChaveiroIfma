package controles;

import java.util.List;

import dao.EmprestimoDAO;
import entidade.Cliente;
import entidade.Emprestimo;
import entidade.Sala;

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
	
	public List<Emprestimo> getEmprestimos(Cliente cliente){
		
		return emprestimoDao.getEmprestimos(cliente);
	}
	
	public List<Emprestimo> getEmprestimos(Sala sala){
		
		return emprestimoDao.getEmprestimos(sala);
	}
	
}
