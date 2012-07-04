package telas;

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
import javax.swing.border.EmptyBorder;

import util.ConstantesComboBox;
import util.MaxLengthDocument;
import util.MyModelTable;
import controles.ClienteControle;
import entidade.Cliente;
import excecoes.SistemaException;
import java.awt.Color;


public class TelaGerenciaCliente extends MyInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel painelCadastro, painelConsulta;
	private JTabbedPane painelTabbed;
	
	private JTextField tfNomeCliente;
	private JTextField tfEmailCliente;
	private JFormattedTextField tfFoneCliente;
	private JFormattedTextField tfCPFCliente;
	
	private JComboBox cbPerfis;
	
	private JButton btnNovo, btnSalvar, btnCancelar, btnExcluir, btnEditar;
	
	private ClienteControle clienteControle = ClienteControle.getInstance(this); 
	private JPasswordField pfSenha;
	private JPasswordField pfRepeteSenha;
	
	private Cliente cliente = new Cliente();
	private JScrollPane scrollConsulta;
	
	private MyModelTable modelTableConsulta;
	private JTextField tfNomeConsulta;
	private JLabel lblErroConsulta;
	
	/**
	 * Create the frame.
	 */
	public TelaGerenciaCliente() {
		super("Cadastro de Clientes");
				
		painelTabbed = new JTabbedPane();
		setContentPane(painelTabbed);
		
		painelCadastro = new JPanel();
		painelCadastro.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelTabbed.add("Cadastro", painelCadastro);
		
		painelConsulta = new JPanel();
		painelConsulta.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelTabbed.add("Consulta", painelConsulta);
		painelConsulta.setLayout(null);
		
		scrollConsulta = new JScrollPane();
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
		
		lblErroConsulta = new JLabel("");
		lblErroConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroConsulta.setForeground(Color.RED);
		lblErroConsulta.setBounds(132, 54, 170, 20);
		painelConsulta.add(lblErroConsulta);
		
		tfNomeConsulta = new JTextField();
		tfNomeConsulta.setBounds(132, 21, 170, 28);
		painelConsulta.add(tfNomeConsulta);
		tfNomeConsulta.setColumns(10);
		tfNomeConsulta.addKeyListener(new OuvinteConsulta());
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(213, 326, 89, 30);
		btnExcluir.addActionListener(this);
		painelConsulta.add(btnExcluir);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(89, 326, 89, 30);
		btnEditar.addActionListener(this);
		painelConsulta.add(btnEditar);
		
		tfNomeCliente = new JTextField(new MaxLengthDocument(Cliente.TAMANHO_NOME), "", 10);
		tfNomeCliente.setBounds(137, 11, 157, 28);
		tfNomeCliente.setColumns(10);
		
		tfEmailCliente = new JTextField();
		tfEmailCliente.setBounds(137, 91, 157, 28);
		tfEmailCliente.setColumns(10);
		
		tfFoneCliente = new JFormattedTextField(utilidades.mascara("(##)####-####"));
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
		
		tfCPFCliente = new JFormattedTextField(utilidades.mascara("###.###.###-##"));
		tfCPFCliente.setBounds(137, 51, 157, 28);
		tfCPFCliente.setColumns(10);
		painelCadastro.setLayout(null);
		
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
		
		pfSenha = new JPasswordField(new MaxLengthDocument(Cliente.TAMANHO_SENHA), "", 10);
		pfSenha.setBounds(137, 171, 157, 28);
		painelCadastro.add(pfSenha);
		
		pfRepeteSenha = new JPasswordField(new MaxLengthDocument(Cliente.TAMANHO_SENHA), "", 10);
		pfRepeteSenha.setBounds(137, 211, 157, 28);
		painelCadastro.add(pfRepeteSenha);
		
		this.setBounds(100, 100, 405, 424);
		utilidades.formataJanela(this, "/imagens/usuarios.png");
		
		carregarTabela();
		this.setVisible(true);
	}
	
	public static MyInternalFrame getInstance() {
		
		if (tela == null) {
            tela = new TelaGerenciaCliente();
        }
		
        return tela;
	}
	
	private class OuvinteConsulta implements KeyListener{

		@Override
		public void keyReleased(KeyEvent e) {
			
			carregarTabela();
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) { }

		@Override
		public void keyPressed(KeyEvent e) { }
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnSalvar){
			
			//FIXME retornar o objeto salvo para o objeto cliente
			
			if(cliente.getIdcliente() == null){
				clienteControle.inserir();
			} else{
				clienteControle.editar();
			}
			
			tfNomeConsulta.setText("");
			carregarTabela();
		}
		
		else if(e.getSource() == btnCancelar){
			cancelar();
		}
		
		else if(e.getSource() == btnNovo){
			cliente = new Cliente();
			limparCampos();
		}
		else if(e.getSource() == btnExcluir){
			clienteControle.excluir();
			tfNomeConsulta.setText("");
			carregarTabela();
		}
		else if(e.getSource() == btnEditar){
			
			cliente = (Cliente) modelTableConsulta.getKeySelected();
			setCliente();
			painelTabbed.setSelectedIndex(0);
		}
	}

	private void limparCampos(){
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
	
	private void cancelar(){
		this.dispose();
	}
	
	private void carregarTabela(){
		
		try {
			
			modelTableConsulta.clear();
			
			List<Cliente> list = clienteControle.consultaPorNome(tfNomeConsulta.getText());
			
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

	private void setCliente(){
		
		tfNomeCliente.setText(cliente.getNome());
		tfCPFCliente.setText(cliente.getCpf());
		tfEmailCliente.setText(cliente.getEmail());
		tfFoneCliente.setText(cliente.getTelefone());
		pfSenha.setText(cliente.getSenha());
		pfRepeteSenha.setText(cliente.getSenha());
		
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
