package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import util.MaxLengthDocument;
import util.Utilidades;
import dao.ClienteDAO;
import dao.EmprestimoDAO;
import dao.SalaDAO;
import entidade.Cliente;
import entidade.Emprestimo;
import entidade.Sala;

public class DialogEmprestimo extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private Utilidades utilidades = Utilidades.getInstance();
	
	private final JPanel painelEmprestimo = new JPanel();
	private JPasswordField pfSenha;
	
	private JFormattedTextField tfCpf;
	
	private JButton btnOk, btnCancelar;

	private ClienteDAO clienteDao = ClienteDAO.getInstance();
	private EmprestimoDAO emprestimoDao = EmprestimoDAO.getInstance();
	private SalaDAO salaDao = SalaDAO.getInstance();
	private Sala sala;
	
	/**
	 * Create the dialog.
	 */
	public DialogEmprestimo(Sala sala) {
		this.sala = sala;
		setBounds(100, 100, 325, 196);

		this.setContentPane(painelEmprestimo);
		painelEmprestimo.setLayout(null);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(41, 112, 89, 30);
		btnOk.addActionListener(this);
		painelEmprestimo.add(btnOk);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(174, 112, 89, 30);
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
		
		pfSenha = new JPasswordField(new MaxLengthDocument(Cliente.TAMANHO_SENHA), "", 10);
		pfSenha.setBounds(109, 53, 139, 28);
		painelEmprestimo.add(pfSenha);
		
		tfCpf = new JFormattedTextField(utilidades.mascara("###.###.###-##"));
		tfCpf.setBounds(109, 11, 139, 28);
		painelEmprestimo.add(tfCpf);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnOk){
			
			Cliente cliente = getCliente();
			cliente = clienteDao.getClienteByLogin(cliente);
			
			if(cliente != null){
				
				Emprestimo emprestimo = new Emprestimo();
				
				emprestimo.setSala(sala);
				emprestimo.setCliente(cliente);
				emprestimo.setDataRetirada(new Date());
				
				salaDao.mudarStatus(sala.getIdsala(), Sala.STATUS_ABERTA);
				emprestimoDao.inserir(emprestimo);
				
				this.dispose();
			}
			else{
				
				utilidades.msgError("Login inválido");
			}
			
		}
		
		else if(e.getSource() == btnCancelar){
			
			this.dispose();
		}
		
	}
	
	private Cliente getCliente(){
		
		Cliente c = new Cliente();
		
		c.setCpf(tfCpf.getText());
		c.setSenha(new String(pfSenha.getPassword()));
		
		return c;
	}
	
}
