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
			
			camposObrigatorios();
			
			Cliente cliente = getCliente();
			
			clienteDao.inserir(cliente);
			
			utilidades.msgInformation("Cliente cadastrado com sucesso!");
			
		} catch (SistemaException e) {
			
			utilidades.msgWarning(e.getMessage());
		}
		
		
	}
	
	private Cliente getCliente() throws SistemaException {
		
		Cliente cliente = tela.getCliente();
		
		cliente.setCpf(tela.getTfCPFCliente().getText()); //FIXME validar CPF aqui
		
		//validando cpf
		if(!utilidades.validacpf(cliente.getCpf())){
			throw new SistemaException("CPF invalido!");
		}
		
		String senha1 = new String(tela.getPfSenha().getPassword());
		String senha2 = new String(tela.getPfRepeteSenha().getPassword());
		
		//validando se as senhas digitadas sao iguais
		if(!senha1.equals(senha2)){
			throw new SistemaException("As senhas nao sao iguais!");
		}
		cliente.setSenha(senha1);
		
		cliente.setEmail(tela.getTfEmailCliente().getText());
		cliente.setTelefone(tela.getTfFoneCliente().getText());
		cliente.setNome(tela.getTfNomeCliente().getText());
		
		System.out.println("perfil selecionado: " + (Integer) utilidades.getValueFromCombo(tela.getCbPerfis()));
		
		cliente.setPerfil((Integer) utilidades.getValueFromCombo(tela.getCbPerfis())); //usar esse metodo para pegar valor da combo
		
		return cliente;
	}
	
	private void camposObrigatorios() throws SistemaException {
		
		if(tela.getTfNomeCliente().getText().trim().equals(""))
			throw new SistemaException("O nome do cliente e' obrigatorio");
		
		if(tela.getTfCPFCliente().getText().trim().length()<14)
			throw new SistemaException("CPF digitado incorretamente");
		
		if(new String(tela.getPfSenha().getPassword()).trim().equals(""))
			throw new SistemaException("O senha do cliente e' obrigatoria");
		
		if(new String(tela.getPfSenha().getPassword()).trim().equals(""))
			throw new SistemaException("Repita a senha");
		
		//FIXME as senhas devem ter um numero minimo de caracteres tambem 
		
	}
	
}
