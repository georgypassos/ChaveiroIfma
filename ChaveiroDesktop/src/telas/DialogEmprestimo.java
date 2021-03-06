package telas;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import util.MaxLengthDocument;
import controles.EmprestimoControle;
import entidade.Cliente;
import entidade.Sala;
import excecoes.SistemaException;

public class DialogEmprestimo extends MyDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private final JPanel painelEmprestimo = new JPanel();
	private JPasswordField pfSenha;
	
	private JFormattedTextField tfCpf;
	
	private JButton btnOk, btnCancelar;

	private EmprestimoControle emprestimoControle = EmprestimoControle.getInstance();
	private Sala sala;
	
	/**
	 * Create the dialog.
	 */
	public DialogEmprestimo(Sala sala) {
		super("Novo Empr\u00E9stimo");
		this.sala = sala;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogEmprestimo.class.getResource("/imagens/pnemprestimo.png")));
		
		setBounds(100, 100, 325, 196);
		
		this.setContentPane(painelEmprestimo);
		painelEmprestimo.setLayout(null);
		
		btnOk = new JButton("OK");
		btnOk.setIcon(new ImageIcon(DialogEmprestimo.class.getResource("/imagens/btok.png")));
		btnOk.setBounds(41, 112, 98, 30);
		btnOk.addActionListener(this);
		painelEmprestimo.add(btnOk);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(DialogEmprestimo.class.getResource("/imagens/btcancel.png")));
		btnCancelar.setBounds(163, 112, 110, 30);
		btnCancelar.addActionListener(this);
		painelEmprestimo.add(btnCancelar);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setBounds(10, 18, 89, 14);
		painelEmprestimo.add(lblCpf);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(10, 60, 89, 14);
		painelEmprestimo.add(lblSenha);
		
		pfSenha = new JPasswordField(new MaxLengthDocument(Cliente.TAMANHO_MAX_SENHA), "", 10);
		pfSenha.setBounds(109, 53, 139, 28);
		painelEmprestimo.add(pfSenha);
		
		tfCpf = new JFormattedTextField(utilidades.mascara("###.###.###-##"));
		tfCpf.setBounds(109, 11, 139, 28);
		tfCpf.addKeyListener(new OuvinteCpf());
		painelEmprestimo.add(tfCpf);
		
		this.getRootPane().setDefaultButton(btnOk);
		this.setVisible(true);
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
		
		if(e.getSource() == btnOk){
			
			try {
				emprestimoControle.efetuarEmprestimo(this);
				this.dispose();
			} catch (SistemaException ex) {
				utilidades.msgError(ex.getMessage());
			}
			
		}
		
		else if(e.getSource() == btnCancelar){
			this.dispose();
		}
		
	}

	public JPasswordField getPfSenha() {
		return pfSenha;
	}

	public JFormattedTextField getTfCpf() {
		return tfCpf;
	}

	public Sala getSala() {
		return sala;
	}
	
}
