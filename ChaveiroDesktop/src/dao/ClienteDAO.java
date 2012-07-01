package dao;

import java.util.List;

import entidade.Cliente;

public class ClienteDAO extends Dao<Cliente> {
	
	private static final ClienteDAO clienteDAO = new ClienteDAO();
	
	private ClienteDAO(){
		super(Cliente.class);
	}
	
	public static ClienteDAO getInstance(){
		
		return clienteDAO;
	}
	
	//método de teste
	public void inserir(Cliente cliente){
		
		insert(cliente);
        
        System.out.println("Cliente salvo com sucesso!");
	}
	
	public Cliente getClienteByLogin(Cliente cliente){
		
		String sql = "FROM Cliente c WHERE c.cpf = :p0 AND c.senha = :p1";
		
		List<Cliente> list = search(sql, cliente.getCpf(), cliente.getSenha());
		
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}
	
	public Cliente getCliente(int id){
		
		return get(id);
	}
	
	//método main só para criar o banco
	public static void main(String[] args) {
		ClienteDAO c = ClienteDAO.getInstance();
		
		String sql = "FROM Cliente c WHERE c.nome like :p0 AND c.senha = :p1";
		
		List<Cliente> list = c.search(sql, "%geo%", "georgysenhaa");
		
		for (Cliente cliente : list) {
			System.out.println(cliente.getIdcliente() + " - " + cliente.getNome());
		}
		
	}
	
}
