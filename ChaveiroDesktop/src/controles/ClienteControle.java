package controles;

import java.util.List;

import javax.swing.JOptionPane;

import telas.TelaGerenciaCliente;
import dao.ClienteDAO;
import entidade.Cliente;
import excecoes.SistemaException;

public class ClienteControle extends Controle {
	
	private ClienteDAO clienteDao = ClienteDAO.getInstance();

	private static ClienteControle clienteControle;
	
	private ClienteControle(){ }
	
	public static ClienteControle getInstance(){
		
		if(clienteControle == null){
			clienteControle = new ClienteControle();
		}
		
		return clienteControle;
	}
	
	public Cliente salvar(TelaGerenciaCliente tela){
		
		try {

			Cliente cliente = getCliente(tela);
			
			validarCampos(tela, cliente);
			
			cliente = clienteDao.salvar(cliente);
			
			utilidades.msgInformation("Cliente salvo com sucesso!");
			
			return cliente;
			
		} catch (SistemaException e) {
			utilidades.msgWarning(e.getMessage());
		}
		
		return tela.getCliente();
		
	}
	
	public void excluir(TelaGerenciaCliente tela){
		
		try {
			
			Cliente cliente = (Cliente) tela.getModelTableConsulta().getSelectedKey();

			if(utilidades.getYesNoOption("Deseja realmente excluir " + cliente.getNome() + " ?") == JOptionPane.YES_OPTION){
				
				clienteDao.excluir(cliente.getIdcliente());
				
				utilidades.msgInformation("Cliente excluido com sucesso!");
			}
			
		} catch (NullPointerException e) {
			utilidades.msgError("Selecione um item para excluir");
		}
		
	}
	
	public Cliente getClienteByLogin(Cliente cliente) {
		
		//TODO lancar excecao se o cliente for NULL
		
		
		return clienteDao.getClienteByLogin(cliente);
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
	
	private Cliente getCliente(TelaGerenciaCliente tela) {
		
		Cliente cliente = tela.getCliente();
		
		cliente.setCpf(tela.getTfCPFCliente().getText());
		cliente.setSenha(new String(tela.getPfSenha().getPassword()));
		cliente.setEmail(tela.getTfEmailCliente().getText());
		cliente.setTelefone(tela.getTfFoneCliente().getText());
		cliente.setNome(tela.getTfNomeCliente().getText());
		cliente.setPerfil((Integer) utilidades.getValueFromCombo(tela.getCbPerfis())); 
		
		return cliente;
	}

	private void validarCampos(TelaGerenciaCliente tela, Cliente cliente) throws SistemaException {

		/** verficando campos obrigatorios */
		
		//TODO remover m�scaras dos objetos JFormattedTextField para inserir no banco
		
		if(cliente.getNome().trim().equals(""))
			throw new SistemaException("O nome do cliente e' obrigatorio");
		
		if(cliente.getSenha().trim().equals(""))
			throw new SistemaException("O senha do cliente e' obrigatoria");
		
		if(new String(tela.getPfRepeteSenha().getPassword()).trim().equals(""))
			throw new SistemaException("Repita a senha");
		
		/** validando campos */
		
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
