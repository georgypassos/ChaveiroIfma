package dao;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import entidade.Cliente;
import entidade.Emprestimo;
import entidade.Sala;

public class EmprestimoDAO extends Dao<Emprestimo> {

	private static EmprestimoDAO emprestimoDAO;

	private EmprestimoDAO() {
		super(Emprestimo.class);
	}

	public static EmprestimoDAO getInstance() {

		if (emprestimoDAO == null) {
			emprestimoDAO = new EmprestimoDAO();
		}

		return emprestimoDAO;
	}

	public Emprestimo salvar(Emprestimo e) {

		return merge(e);
	}

	public List<Emprestimo> getEmprestimos(Cliente cliente, Integer mes, Boolean hoje) {

		String sql = "FROM Emprestimo e WHERE (e.clienteEntrega.idcliente = :p0 OR e.clienteRetirada.idcliente = :p0)";

		if (mes != null) {
			sql += " AND MONTH(e.dataRetirada) = :p1 ";
			
			return search(sql, "e.dataRetirada DESC", cliente.getIdcliente(), mes);
		}
		else if (hoje != null && hoje) {
			List<Date> list = datasHoje();
			sql += " AND e.dataRetirada >= :p1 AND e.dataRetirada <= :p2 ";
			
			return search(sql, "e.dataRetirada DESC", list.get(0), list.get(1));
		}

		return search(sql, "e.dataRetirada DESC", cliente.getIdcliente());
	}
	
	public static void main(String[] args) {
		Cliente c = new Cliente();
		c.setIdcliente(1);
		
		List<Emprestimo> list = EmprestimoDAO.getInstance().getEmprestimos(c, null, true);
		
		for(Emprestimo e : list){
			System.out.println("  ->>> "+e.getDataRetirada());
		}
		
	}

	public List<Emprestimo> getEmprestimos(Sala sala, Integer mes, Boolean hoje) {

		String sql = "FROM Emprestimo e WHERE e.sala.idsala = :p0 ORDER BY e.dataRetirada DESC ";

		if (mes != null) {
			sql += " AND MONTH(e.dataRetirada) = :p1 ";
			
			return search(sql, "e.dataRetirada DESC", sala.getIdsala(), mes);
		}
		else if (hoje != null && hoje) {
			List<Date> list = datasHoje();
			sql += " AND e.dataRetirada >= :p1 AND e.dataRetirada <= :p2 ";
			
			return search(sql, "e.dataRetirada DESC", list.get(0), list.get(1));
		}

		return search(sql, "e.dataRetirada DESC", sala.getIdsala());
	}

	private List<Date> datasHoje() {

		Calendar data = Calendar.getInstance();
		data.setTime(new Date());
		
		Calendar dInicio = (Calendar) data.clone();
		Calendar dFim = (Calendar) data.clone();

		dInicio.set(Calendar.HOUR_OF_DAY, 0);
		dInicio.set(Calendar.MINUTE, 0);
		dInicio.set(Calendar.SECOND, 0);

		dFim.set(Calendar.HOUR_OF_DAY, 23);
		dFim.set(Calendar.MINUTE, 59);
		dFim.set(Calendar.SECOND, 59);

		System.out.println(dInicio.getTime());
		System.out.println(dFim.getTime());
		
		return Arrays.asList(dInicio.getTime(), dFim.getTime());
	}

}
