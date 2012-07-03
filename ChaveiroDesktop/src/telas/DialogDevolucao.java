package telas;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.MaxLengthDocument;
import util.Utilidades;
import dao.EmprestimoDAO;
import dao.SalaDAO;
import entidade.Cliente;
import entidade.Emprestimo;
import entidade.Sala;

public class DialogDevolucao extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private Utilidades utilidades = Utilidades.getInstance();
	
	private final JPanel painelDevolucao = new JPanel();
	private JPasswordField pfSenha;
	private JTextField tfNomeCliente;
	private JButton btnOk, btnCancelar;

	private EmprestimoDAO emprestimoDao = EmprestimoDAO.getInstance();
	private SalaDAO salaDao = SalaDAO.getInstance();
	private Emprestimo emprestimo;
	
	/**
	 * Create the dialog.
	 */
	public DialogDevolucao(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogDevolucao.class.getResource("/imagens/pndevolucao.png")));
		setTitle("Devolu\u00E7\u00E3o");
		
		setBounds(100, 100, 325, 196);
		
		this.setContentPane(painelDevolucao);
		painelDevolucao.setLayout(null);
		
		btnOk = new JButton("OK");
		btnOk.setIcon(new ImageIcon(DialogEmprestimo.class.getResource("/imagens/btok.png")));
		btnOk.setBounds(41, 112, 98, 30);
		btnOk.addActionListener(this);
		painelDevolucao.add(btnOk);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(DialogEmprestimo.class.getResource("/imagens/btcancel.png")));
		btnCancelar.setBounds(163, 112, 110, 30);
		btnCancelar.addActionListener(this);
		painelDevolucao.add(btnCancelar);
		
		JLabel lblCpf = new JLabel("Nome:");
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setBounds(10, 18, 89, 14);
		painelDevolucao.add(lblCpf);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(10, 60, 89, 14);
		painelDevolucao.add(lblSenha);
		
		pfSenha = new JPasswordField(new MaxLengthDocument(Cliente.TAMANHO_SENHA), "", 10);
		pfSenha.setBounds(109, 53, 139, 28);
		painelDevolucao.add(pfSenha);
		
		tfNomeCliente = new JTextField();
		tfNomeCliente.setEditable(false);
		tfNomeCliente.setBounds(109, 11, 139, 28);
		painelDevolucao.add(tfNomeCliente);
		
		//seta o nome do cliente
		tfNomeCliente.setText(emprestimo.getCliente().getNome());
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getRootPane().setDefaultButton(btnOk);
		this.setModal(true);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnOk){
			
			String senha = new String(pfSenha.getPassword());
			
			if(senha.equals(emprestimo.getCliente().getSenha())){

				emprestimo.setDataEntrega(new Date());
				
				emprestimoDao.atualizar(emprestimo);
				
				salaDao.mudarStatus(emprestimo.getSala().getIdsala(), Sala.STATUS_FECHADA);
				
				this.dispose();
				
			}
			else{

				utilidades.msgError("Senha incorreta!");
			}
			
		}
		
		else if(e.getSource() == btnCancelar){
			
			this.dispose();
		}
		
	}
	
}
