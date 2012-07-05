package controles;

import java.util.List;

import telas.TelaGerenciaCliente;
import dao.ClienteDAO;
import entidade.Cliente;
import excecoes.SistemaException;

public class ClienteControle extends Controle {
	
	private TelaGerenciaCliente tela;
	private ClienteDAO clienteDao = ClienteDAO.getInstance();

	private static ClienteControle clienteControle;
	
	private ClienteControle(Object view){
		super(view);
		tela = (TelaGerenciaCliente) this.view;
	}
	
	public static ClienteControle getInstance(Object view){
		
		if(clienteControle == null){
			clienteControle = new ClienteControle(view);
		}
		
		return clienteControle;
	}
	
	public Cliente inserir(){
		
		try {

			Cliente cliente = getCliente();
			
			validarCampos(cliente);
			
			cliente = clienteDao.inserir(cliente);
			
			utilidades.msgInformation("Cliente cadastrado com sucesso!");
			
			return cliente;
			
		} catch (SistemaException e) {
			utilidades.msgWarning(e.getMessage());
		}
		return null;
		
	}
	
	public Cliente editar(){
		
		try {

			Cliente cliente = getCliente();
			
			validarCampos(cliente);

			cliente = clienteDao.atualizar(cliente);
			
			utilidades.msgInformation("Cliente salvo com sucesso!");
			
			return cliente;
			
		} catch (SistemaException e) {
			utilidades.msgWarning(e.getMessage());
		}
		return null;
		
	}
	
	public void excluir(){
		
		try {
			
			Cliente cliente = (Cliente) tela.getModelTableConsulta().getKeySelected();
			
			clienteDao.excluir(cliente);
			
			utilidades.msgInformation("Cliente excluido com sucesso!");
			
		} catch (NullPointerException e) {
			utilidades.msgError("Selecione um item para excluir");
		}
		
	}
	
	public List<Cliente> consultaPorNome(String nome) throws SistemaException{
		
		List<Cliente> list = clienteDao.consultaPorNome(nome);
		
		if(list != null && list.size()>0){
			return list;
		}
		else{
			throw new SistemaException("Sem resultados!");
		}
		
	}
	
	private Cliente getCliente() {
		
		Cliente cliente = tela.getCliente();
		
		cliente.setCpf(tela.getTfCPFCliente().getText());
		cliente.setSenha(new String(tela.getPfSenha().getPassword()));
		cliente.setEmail(tela.getTfEmailCliente().getText());
		cliente.setTelefone(tela.getTfFoneCliente().getText());
		cliente.setNome(tela.getTfNomeCliente().getText());
		cliente.setPerfil((Integer) utilidades.getValueFromCombo(tela.getCbPerfis())); //usar esse metodo para pegar valor da combo
		
		return cliente;
	}
	
	private void validarCampos(Cliente cliente) throws SistemaException {

		/** verficando campos obrigatorios */
		
		//TODO verificar porque quando o nome do cliente esta' preenchido, mas esta' vindo vazio para esse IF
		
		if(cliente.getNome().trim().equals(""))
			throw new SistemaException("O nome do cliente e' obrigatorio");
		
		if(cliente.getCpf().trim().length()<14)
			throw new SistemaException("CPF digitado incorretamente");
		
		if(cliente.getSenha().trim().equals(""))
			throw new SistemaException("O senha do cliente e' obrigatoria");
		
		if(new String(tela.getPfRepeteSenha().getPassword()).trim().equals(""))
			throw new SistemaException("Repita a senha");
		
		/** validando campos */
		
		//validando telefone
		int tamFone = cliente.getTelefone().trim().length();
		
		System.out.println("tamanho campo telefone: " + tamFone);
		
		if(tamFone>3 && tamFone<13)
			throw new SistemaException("Telefone digitado incorretamente");
		
		//validando cpf
		if(!utilidades.validacpf(cliente.getCpf()))
			throw new SistemaException("CPF invalido");
		
		if(clienteDao.existeCpf(cliente))
			throw new SistemaException("CPF ja cadastrado");
		
		String senha1 = new String(cliente.getSenha());
		String senha2 = new String(tela.getPfRepeteSenha().getPassword());
		
		if(senha1.length() < Cliente.TAMANHO_MIN_SENHA)
			throw new SistemaException("A senha deve ter no minimo " + Cliente.TAMANHO_MIN_SENHA + " caracteres");
		
		//validando se as senhas digitadas sao iguais
		if(!senha1.equals(senha2))
			throw new SistemaException("As senhas nao sao iguais");
		
	}
	
}
