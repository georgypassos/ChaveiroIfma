package controles;

import telas.TelaGerenciaCliente;
import dao.ClienteDAO;
import entidade.Cliente;
import excecoes.SistemaException;

public class ClienteControle extends Controle{
	
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
	
	public void inserir(){
		
		try {
			
			validarCampos();
			
			Cliente cliente = getCliente();
			
			clienteDao.inserir(cliente);
			
			utilidades.msgInformation("Cliente cadastrado com sucesso!");
			
		} catch (SistemaException e) {
			
			utilidades.msgWarning(e.getMessage());
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
	
	private void validarCampos() throws SistemaException {

		/** verficando campos obrigatorios */
		
		if(tela.getTfNomeCliente().getText().trim().equals(""))
			throw new SistemaException("O nome do cliente e' obrigatorio");
		
		if(tela.getTfCPFCliente().getText().trim().length()<14)
			throw new SistemaException("CPF digitado incorretamente");
		
		if(new String(tela.getPfSenha().getPassword()).trim().equals(""))
			throw new SistemaException("O senha do cliente e' obrigatoria");
		
		if(new String(tela.getPfRepeteSenha().getPassword()).trim().equals(""))
			throw new SistemaException("Repita a senha");
		
		//FIXME as senhas devem ter um numero minimo de caracteres tambem 
		
		
		/** validando campos */
		
		//validando cpf
		if(!utilidades.validacpf(tela.getTfCPFCliente().getText()))
			throw new SistemaException("CPF invalido!");
		
		String senha1 = new String(tela.getPfSenha().getPassword());
		String senha2 = new String(tela.getPfRepeteSenha().getPassword());
		
		//validando se as senhas digitadas sao iguais
		if(!senha1.equals(senha2))
			throw new SistemaException("As senhas nao sao iguais!");
		
	}
	
}
