package dao;

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

	public List<Emprestimo> getEmprestimos(Cliente cliente) {

		String sql = "FROM Emprestimo e WHERE e.clienteEntrega.idcliente = :p0 OR e.clienteRetirada.idcliente = :p0 ORDER BY e.dataRetirada DESC ";

		return search(sql, cliente.getIdcliente());
	}

	public List<Emprestimo> getEmprestimos(Sala sala) {

		String sql = "FROM Emprestimo e WHERE e.sala.idsala = :p0 ORDER BY e.dataRetirada DESC ";

		return search(sql, sala.getIdsala());
	}

}
