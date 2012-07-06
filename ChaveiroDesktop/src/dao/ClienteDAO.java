package dao;

import java.util.List;

import entidade.Cliente;

public class ClienteDAO extends Dao<Cliente> {

	private static ClienteDAO clienteDAO;

	private ClienteDAO() {
		super(Cliente.class);
	}

	public static ClienteDAO getInstance() {
		if(clienteDAO == null){
			clienteDAO = new ClienteDAO();
		}
		
		return clienteDAO;
	}

	public Cliente salvar(Cliente cliente) {

		return merge(cliente);
	}

	public void excluir(int id) {
		
		remove(id);
	}

	public Cliente getCliente(int id) {

		return get(id);
	}

	public List<Cliente> consultaPorNome(String nome){
		
		String sql = "FROM Cliente c WHERE c.nome LIKE :p0 ORDER BY c.nome ASC";
		
		return search(sql, null, "%"+nome+"%");
	}
	
	public boolean existeCpf(Cliente cliente){
		
		List<Cliente> list = null;
		
		String sql = "FROM Cliente c WHERE c.cpf = :p0";
		
		if(cliente.getIdcliente() != null){
			sql +=  " AND c.idcliente != :p1" ;
			list = search(sql, null, cliente.getCpf(), cliente.getIdcliente());
		}
		else{
			list = search(sql, null, cliente.getCpf());
		}
		
		if(list != null && list.size()>0){
			return true;
		}
		
		return false;
	}

	public Cliente getClienteByLogin(Cliente cliente) {

		String sql = "FROM Cliente c WHERE c.cpf = :p0 AND c.senha = :p1";

		List<Cliente> list = search(sql, null, cliente.getCpf(), cliente.getSenha());

		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

}
