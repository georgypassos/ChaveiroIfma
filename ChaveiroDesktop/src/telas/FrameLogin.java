package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import util.MaxLengthDocument;
import util.Utilidades;
import controles.ClienteControle;
import entidade.Cliente;

public class FrameLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 6361221735807609119L;

	private Utilidades utilidades = Utilidades.getInstance();
	
	private JPanel painelLogin;

	private JPasswordField pfSenha;
	private JFormattedTextField tfCpf;
	private JButton btnLogin, btnFechar;

	/**
	 * Create the frame.
	 */
	public FrameLogin() {
		setBounds(100, 100, 349, 352);
		painelLogin = new JPanel();
		
//		TODO setIconImage(Toolkit.getDefaultToolkit().getImage(FrameLogin.class.getResource("/imagens/pnlogin.png")));
		
		this.setContentPane(painelLogin);
		painelLogin.setLayout(null);
		
		btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(DialogEmprestimo.class.getResource("/imagens/btok.png")));
		btnLogin.setBounds(55, 267, 98, 30);
		btnLogin.addActionListener(this);
		painelLogin.add(btnLogin);
		
		btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon(DialogEmprestimo.class.getResource("/imagens/btcancel.png")));
		btnFechar.setBounds(177, 267, 110, 30);
		btnFechar.addActionListener(this);
		painelLogin.add(btnFechar);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setBounds(24, 173, 89, 14);
		painelLogin.add(lblCpf);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(24, 215, 89, 14);
		painelLogin.add(lblSenha);
		
		pfSenha = new JPasswordField(new MaxLengthDocument(Cliente.TAMANHO_MAX_SENHA), "", 10);
		pfSenha.setBounds(123, 208, 139, 28);
		painelLogin.add(pfSenha);
		
		tfCpf = new JFormattedTextField(utilidades.mascara("###.###.###-##"));
		tfCpf.setBounds(123, 166, 139, 28);
		tfCpf.addKeyListener(new OuvinteCpf());
		tfCpf.setFocusLostBehavior(JFormattedTextField.PERSIST);
		painelLogin.add(tfCpf);
		
		utilidades.centralizaJanela(this, 140);
		this.getRootPane().setDefaultButton(btnLogin);
		this.setResizable(false);
		this.setVisible(true);
		
		//FIXME sair do sistema quando o cliente fechar a janela
		
	}
	
	private class OuvinteCpf implements KeyListener{

		@Override
		public void keyReleased(KeyEvent e) {
			
			if(tfCpf.getText().trim().length() >= 14){
				
				pfSenha.requestFocus();
				
			}
		}
		
		@Override
		public void keyTyped(KeyEvent e) { }

		@Override
		public void keyPressed(KeyEvent e) { }

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnLogin){
			
			//FIXME pegar excecao de login invalido aqui!
			
			Cliente cliente = ClienteControle.getInstance().getClienteByLogin(getCliente());
			
			if(cliente != null){

				if(cliente.getPerfil() != Cliente.PERFIL_CLIENTE){
					
					FramePrincipal.getInstance().setUsuarioLogado(cliente);
					this.dispose();
					
				}
				else{
					utilidades.msgError("Você não tem permissão para acessar o sistema");
				}
				
			}
			
			else{
				utilidades.msgError("Login invalido!");
			}
			
		}
		
		else if(e.getSource() == btnFechar){
			
			System.exit(0);
		}
		
	}

	private Cliente getCliente(){
		
		Cliente c = new Cliente();
		
		c.setCpf(tfCpf.getText());
		c.setSenha(new String(pfSenha.getPassword()));
		
		return c;
	}
	
}
