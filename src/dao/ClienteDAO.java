package dao;

import entidade.Cliente;

public class ClienteDAO extends Dao {
	
	private static final ClienteDAO clienteDAO = new ClienteDAO();
	
	public static ClienteDAO getInstance(){
		return clienteDAO;
	}
	
	//m�todo de teste
	public void inserir(Cliente cliente){
		
		em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        
        System.out.println("Cliente salvo com sucesso!");
        
	}
	
	//m�todo main s� para criar o banco
	public static void main(String[] args) {
		ClienteDAO c = ClienteDAO.getInstance();
		
		Cliente cliente = new Cliente();
		
		cliente.setNome("Tayna");
		cliente.setSenha("taynasenhaa");
		
		c.inserir(cliente);
		
	}
	
}
