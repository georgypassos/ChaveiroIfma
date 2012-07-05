package dao;

import java.util.List;

import entidade.Cliente;

public class ClienteDAO extends Dao<Cliente> {

	private static final ClienteDAO clienteDAO = new ClienteDAO();

	private ClienteDAO() {
		super(Cliente.class);
	}

	public static ClienteDAO getInstance() {

		return clienteDAO;
	}

	// m�todo de teste
	public Cliente inserir(Cliente cliente) {

		return merge(cliente);
	}

	public Cliente atualizar(Cliente cliente) {

		return merge(cliente);
	}

	public void excluir(Cliente cliente) {
		
		remove(cliente);
		
	}
	
	public List<Cliente> consultaPorNome(String nome){
		
		String sql = "FROM Cliente c WHERE c.nome LIKE :p0 ORDER BY c.nome";
		
		return search(sql, "%"+nome+"%");
	}
	
	public boolean existeCpf(Cliente cliente){
		
		String sql = "FROM Cliente c WHERE c.cpf = :p0 AND c.idcliente != :p1";
		
		List<Cliente> list = search(sql, cliente.getCpf(), cliente.getIdcliente());
		
		if(list != null && list.size()>0){
			return true;
		}
		
		return false;
	}

	public Cliente getClienteByLogin(Cliente cliente) {

		String sql = "FROM Cliente c WHERE c.cpf = :p0 AND c.senha = :p1";

		List<Cliente> list = search(sql, cliente.getCpf(), cliente.getSenha());

		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public Cliente getCliente(int id) {

		return get(id);
	}

}
