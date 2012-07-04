package controles;

import java.util.List;

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
	
	public void editar(){
		
		try {
			
			//FIXME primeiro pegar o cliente, depois validar os campos
			Cliente cliente = getCliente();
			
			validarCampos();
			
			clienteDao.atualizar(cliente);
			
			utilidades.msgInformation("Cliente salvo com sucesso!");
			
		} catch (SistemaException e) {
			utilidades.msgWarning(e.getMessage());
		}
		
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
	
	private void validarCampos() throws SistemaException {

		/** verficando campos obrigatorios */
		
		//TODO verificar porque quando o nome do cliente esta' preenchido, mas esta' vindo vazio para esse IF
		
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
		
		//FIXME validar o telefone (apenas se ele tiver sido digitado)
		
		//validando cpf
		if(!utilidades.validacpf(tela.getTfCPFCliente().getText()))
			throw new SistemaException("CPF invalido");
		
		if(clienteDao.existeCpf(tela.getTfCPFCliente().getText()))
			throw new SistemaException("CPF ja cadastrado");
		
		String senha1 = new String(tela.getPfSenha().getPassword());
		String senha2 = new String(tela.getPfRepeteSenha().getPassword());
		
		//validando se as senhas digitadas sao iguais
		if(!senha1.equals(senha2))
			throw new SistemaException("As senhas nao sao iguais");
		
	}
	
}
