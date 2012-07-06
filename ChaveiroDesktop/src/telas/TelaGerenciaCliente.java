package telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import util.ConstantesComboBox;
import util.MaxLengthDocument;
import util.MyModelTable;
import controles.ClienteControle;
import entidade.Cliente;
import excecoes.SistemaException;


public class TelaGerenciaCliente extends MyInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private static TelaGerenciaCliente tela;
	
	private JPanel painelCadastro, painelConsulta;
	private JTabbedPane painelTabbed;
	
	private JTextField tfNomeCliente;
	private JTextField tfEmailCliente;
	private JFormattedTextField tfFoneCliente;
	private JFormattedTextField tfCPFCliente;
	
	private JComboBox cbPerfis;
	
	private JButton btnNovo, btnSalvar, btnCancelar, btnExcluir, btnEditar;
	
	private JPasswordField pfSenha;
	private JPasswordField pfRepeteSenha;

	private MyModelTable modelTableConsulta;
	private JTextField tfNomeConsulta;
	private JLabel lblErroConsulta;
	
	private Cliente cliente;
	private ClienteControle clienteControle = ClienteControle.getInstance(); 
	
	/**
	 * Create the frame.
	 */
	private TelaGerenciaCliente() {
		super("Cadastro de Clientes", "/imagens/mnusuarios.png");
				
		painelTabbed = new JTabbedPane();
		setContentPane(painelTabbed);
		
		painelCadastro = new JPanel();
		painelCadastro.setLayout(null);
		painelTabbed.add("Cadastro", painelCadastro);
		
		painelConsulta = new JPanel();
		painelConsulta.setLayout(null);
		painelTabbed.add("Consulta", painelConsulta);
		
		// --- painel de cadastro
		
		tfNomeCliente = new JTextField(new MaxLengthDocument(Cliente.TAMANHO_MAX_NOME), "", 10);
		tfNomeCliente.setBounds(137, 11, 157, 28);
		tfNomeCliente.setColumns(10);

		tfCPFCliente = new JFormattedTextField(utilidades.mascara("###.###.###-##"));
		tfCPFCliente.setFocusLostBehavior(JFormattedTextField.PERSIST);
		tfCPFCliente.setBounds(137, 51, 157, 28);
		tfCPFCliente.setColumns(10);
		
		tfEmailCliente = new JTextField();
		tfEmailCliente.setBounds(137, 91, 157, 28);
		tfEmailCliente.setColumns(10);
		
		tfFoneCliente = new JFormattedTextField(utilidades.mascara("(##)####-####"));
		tfFoneCliente.setFocusLostBehavior(JFormattedTextField.PERSIST);
		tfFoneCliente.setBounds(137, 131, 157, 28);
		tfFoneCliente.setColumns(10);
		
		cbPerfis = new JComboBox();
		cbPerfis.setBounds(137, 255, 157, 28);
		cbPerfis.setModel(ConstantesComboBox.modelPerfil);

		btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 320, 93, 30);
		btnNovo.setIcon(new ImageIcon(TelaGerenciaCliente.class.getResource("/imagens/btnew.png")));
		btnNovo.addActionListener(this);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(121, 321, 117, 30);
		btnSalvar.setIcon(new ImageIcon(TelaGerenciaCliente.class.getResource("/imagens/btsave.png")));
		btnSalvar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(256, 321, 117, 30);
		btnCancelar.setIcon(new ImageIcon(TelaGerenciaCliente.class.getResource("/imagens/btcancel.png")));
		btnCancelar.addActionListener(this);
		
		JLabel lblNomeCliente = new JLabel("Nome:");
		lblNomeCliente.setBounds(57, 18, 70, 15);
		lblNomeCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		painelCadastro.add(lblNomeCliente);
		
		JLabel lblCPFCliente = new JLabel("CPF:");
		lblCPFCliente.setBounds(57, 58, 70, 15);
		lblCPFCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		painelCadastro.add(lblCPFCliente);
		
		JLabel lblEmailCliente = new JLabel("Email:");
		lblEmailCliente.setBounds(57, 98, 70, 15);
		lblEmailCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		painelCadastro.add(lblEmailCliente);
		
		JLabel lblFoneCliente = new JLabel("Telefone:");
		lblFoneCliente.setBounds(57, 138, 70, 15);
		lblFoneCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		painelCadastro.add(lblFoneCliente);
		
		JLabel lblRepetirSenha = new JLabel("Repetir senha:");
		lblRepetirSenha.setBounds(30, 218, 97, 14);
		lblRepetirSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		painelCadastro.add(lblRepetirSenha);
		
		JLabel lblPerfilCliente = new JLabel("Perfil:");
		lblPerfilCliente.setBounds(57, 262, 70, 15);
		lblPerfilCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		painelCadastro.add(lblPerfilCliente);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(57, 178, 70, 14);
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		painelCadastro.add(lblSenha);
		painelCadastro.add(btnNovo);
		painelCadastro.add(btnSalvar);
		painelCadastro.add(btnCancelar);
		painelCadastro.add(tfFoneCliente);
		painelCadastro.add(tfCPFCliente);
		painelCadastro.add(tfNomeCliente);
		painelCadastro.add(tfEmailCliente);
		painelCadastro.add(cbPerfis);
		
		pfSenha = new JPasswordField(new MaxLengthDocument(Cliente.TAMANHO_MAX_SENHA), "", 10);
		pfSenha.setBounds(137, 171, 157, 28);
		painelCadastro.add(pfSenha);
		
		pfRepeteSenha = new JPasswordField(new MaxLengthDocument(Cliente.TAMANHO_MAX_SENHA), "", 10);
		pfRepeteSenha.setBounds(137, 211, 157, 28);
		painelCadastro.add(pfRepeteSenha);

		// --- painel de consulta

		JScrollPane scrollConsulta = new JScrollPane();
		scrollConsulta.setBounds(10, 85, 364, 220);
		
		JTable tabelaConsulta = new JTable();
		tabelaConsulta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelTableConsulta = new MyModelTable("Nome", "Tipo de perfil");
		modelTableConsulta.setTable(tabelaConsulta);
		scrollConsulta.setViewportView(tabelaConsulta);
		painelConsulta.add(scrollConsulta);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(76, 28, 46, 14);
		painelConsulta.add(lblNome);
		
		tfNomeConsulta = new JTextField(new MaxLengthDocument(Cliente.TAMANHO_MAX_NOME), "", 10);
		tfNomeConsulta.setBounds(132, 21, 170, 28);
		tfNomeConsulta.addKeyListener(new OuvinteConsulta());
		painelConsulta.add(tfNomeConsulta);

		lblErroConsulta = new JLabel("");
		lblErroConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroConsulta.setForeground(Color.RED);
		lblErroConsulta.setBounds(132, 54, 170, 20);
		painelConsulta.add(lblErroConsulta);
		
		btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(TelaGerenciaCliente.class.getResource("/imagens/btedit.png")));
		btnEditar.setBounds(76, 326, 102, 30);
		btnEditar.addActionListener(this);
		painelConsulta.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(TelaGerenciaCliente.class.getResource("/imagens/btdelete.png")));
		btnExcluir.setBounds(213, 326, 102, 30);
		btnExcluir.addActionListener(this);
		painelConsulta.add(btnExcluir);
		
		this.setSize(405, 424);
	}

	@Override
	protected void initialize() {
		carregarTabela("");
		novo();
		utilidades.centralizaJanela(this, 40);
	}
	
	public static TelaGerenciaCliente getInstance() {
		if(tela == null){
			tela = new TelaGerenciaCliente();
		}
		return tela;
	}
	
	private class OuvinteConsulta implements KeyListener{

		@Override
		public void keyReleased(KeyEvent e) {
			carregarTabela(tfNomeConsulta.getText());
		}
		
		@Override
		public void keyTyped(KeyEvent e) { }

		@Override
		public void keyPressed(KeyEvent e) { }
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnSalvar){
			cliente = clienteControle.salvar(this);
			carregarTabela("");
		}
		
		else if(e.getSource() == btnCancelar){
			cancelar();
		}
		
		else if(e.getSource() == btnNovo){
			novo();
		}
		else if(e.getSource() == btnExcluir){
			clienteControle.excluir(this);
			carregarTabela("");
		}
		else if(e.getSource() == btnEditar){
			cliente = (Cliente) modelTableConsulta.getSelectedKey();
			setInfoCliente(cliente);
			painelTabbed.setSelectedIndex(0);
		}
	}

	private void novo(){
		cliente = new Cliente();
		cbPerfis.setSelectedIndex(0);
		limparCampos();
	}
	
	private void cancelar(){
		this.dispose();
	}
	
	private void carregarTabela(String nome){
		
		try {
			
			modelTableConsulta.clear();
			
			List<Cliente> list = clienteControle.consultaPorNome(nome);
			for(Cliente c : list){
				modelTableConsulta.addRowAndKey(c, c.getNome(), c.getPerfilStr());
			}
			
			lblErroConsulta.setText("");
			
		} catch (SistemaException e) {
			
			lblErroConsulta.setText(e.getMessage());
		}
		finally{
			modelTableConsulta.updateUI();
		}
		
	}

	private void setInfoCliente(Cliente cliente){
		
		tfNomeCliente.setText(cliente.getNome());
		tfCPFCliente.setText(cliente.getCpf());
		tfEmailCliente.setText(cliente.getEmail());
		tfFoneCliente.setText(cliente.getTelefone());
		pfSenha.setText(cliente.getSenha());
		pfRepeteSenha.setText(cliente.getSenha());
		cbPerfis.setSelectedIndex(cliente.getPerfil());
		
	}

	public void limparCampos(){
		for(Component c: painelCadastro.getComponents()){    
            if(c instanceof JTextField){    
               ((JTextField) c).setText(null);                   
            }  
            if(c instanceof JFormattedTextField){  
                ((JFormattedTextField) c).setText(null);  
            }  
            if(c instanceof JTextArea){  
               ((JTextArea) c).setText(null);                      
            }
		}
	}
	
	public JTextField getTfNomeCliente() {
		return tfNomeCliente;
	}

	public JTextField getTfEmailCliente() {
		return tfEmailCliente;
	}

	public JTextField getTfFoneCliente() {
		return tfFoneCliente;
	}

	public JFormattedTextField getTfCPFCliente() {
		return tfCPFCliente;
	}

	public JComboBox getCbPerfis() {
		return cbPerfis;
	}

	public JPasswordField getPfSenha() {
		return pfSenha;
	}

	public JPasswordField getPfRepeteSenha() {
		return pfRepeteSenha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public MyModelTable getModelTableConsulta() {
		return modelTableConsulta;
	}

	public JTextField getTfNomeConsulta() {
		return tfNomeConsulta;
	}
	
}
