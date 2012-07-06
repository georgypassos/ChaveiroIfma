package controles;

import java.util.Date;
import java.util.List;

import telas.DialogDevolucao;
import telas.DialogEmprestimo;
import dao.EmprestimoDAO;
import entidade.Cliente;
import entidade.Emprestimo;
import entidade.Sala;
import excecoes.SistemaException;

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
	
	public List<Emprestimo> getEmprestimos(Cliente cliente, Integer mes, Boolean hoje){
		
		return emprestimoDao.getEmprestimos(cliente, mes, hoje);
	}
	
	public List<Emprestimo> getEmprestimos(Sala sala, Integer mes, Boolean hoje){
		
		return emprestimoDao.getEmprestimos(sala, mes, hoje);
	}

	public Emprestimo efetuarEmprestimo(DialogEmprestimo dialog) throws SistemaException {
		
		Cliente cliente = ClienteControle.getInstance().getClienteByLogin(getCliente(dialog));
		
		if(cliente != null){
			
			Sala sala = dialog.getSala();
			
			Emprestimo emprestimo = new Emprestimo();
			
			emprestimo.setSala(sala);
			emprestimo.setClienteRetirada(cliente);
			emprestimo.setDataRetirada(new Date());
			
			//FIXME verificar se quando salvar emprestimo, pode ser salva a sala tambem
			
			SalaControle.getInstance().mudarStatus(sala.getIdsala(), Sala.STATUS_ABERTA);
			
			return emprestimoDao.salvar(emprestimo);
		}
		else{
			throw new SistemaException("Login inválido");
		}
		
	}
	
	public Emprestimo efetuarDevolucao(DialogDevolucao dialog) throws SistemaException {
		
		Cliente cliente = ClienteControle.getInstance().getClienteByLogin(getCliente(dialog));
		
		if(cliente != null){
			
			Emprestimo emprestimo = dialog.getEmprestimo();
			
			emprestimo.setClienteEntrega(cliente);
			emprestimo.setDataEntrega(new Date());
			
			SalaControle.getInstance().mudarStatus(emprestimo.getSala().getIdsala(), Sala.STATUS_FECHADA);
			
			return emprestimoDao.salvar(emprestimo);
		}
		else{
			throw new SistemaException("Login inválido");
		}
		
	}

	private Cliente getCliente(DialogEmprestimo dialog){
		
		Cliente c = new Cliente();
		
		c.setCpf(dialog.getTfCpf().getText());
		c.setSenha(new String(dialog.getPfSenha().getPassword()));
		
		return c;
	}

	private Cliente getCliente(DialogDevolucao dialog){
		
		Cliente c = new Cliente();
		
		c.setCpf(dialog.getTfCpf().getText());
		c.setSenha(new String(dialog.getPfSenha().getPassword()));
		
		return c;
	}
	
}
