package telas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import util.ConstantesComboBox;
import util.MaxLengthDocument;
import controles.ClienteControle;
import entidade.Cliente;


public class TelaGerenciaCliente extends MyInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel painelCadastro;
	private JTextField tfNomeCliente;
	private JTextField tfEmailCliente;
	private JTextField tfFoneCliente;
	private JFormattedTextField tfCPFCliente;
	
	private JComboBox cbPerfis;
	
	private JButton btnNovo, btnSalvar, btnCancelar;
	
	private ClienteControle clienteControle = ClienteControle.getInstance(this); 
	private JPasswordField pfSenha;
	private JPasswordField pfRepeteSenha;
	
	private Cliente cliente = new Cliente();
	
	/**
	 * Create the frame.
	 */
	public TelaGerenciaCliente() {
		super("Cadastro de Clientes");
		
		painelCadastro = new JPanel();
		painelCadastro.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelCadastro);
		
		tfNomeCliente = new JTextField(new MaxLengthDocument(Cliente.TAMANHO_NOME), "", 10);
		tfNomeCliente.setBounds(137, 11, 157, 28);
		tfNomeCliente.setColumns(10);
		
		tfEmailCliente = new JTextField();
		tfEmailCliente.setBounds(137, 91, 157, 28);
		tfEmailCliente.setColumns(10);
		
		tfFoneCliente = new JTextField();
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
		
		this.setBounds(100, 100, 401, 397);
		utilidades.formataJanela(this, "/imagens/usuarios.png");
		this.setVisible(true);
	}
	
	public static MyInternalFrame getInstance() {
		
		if (tela == null) {
            tela = new TelaGerenciaCliente();
        }
		
        return tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnSalvar){
			clienteControle.inserir();
		}
		
		else if(e.getSource() == btnCancelar){
			cancelar();
		}
		
		else if(e.getSource() == btnNovo){
			cliente = new Cliente();
			limparCampos();
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

}
