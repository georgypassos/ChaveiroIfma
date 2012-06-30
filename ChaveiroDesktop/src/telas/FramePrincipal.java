package telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import dao.ClienteDAO;

public class FramePrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	/**
	 * @wbp.nonvisual location=80,339
	 */
	protected final JDesktopPane desktop = new JDesktopPane();

	private JMenu mnCadastro;
	private JButton btnEmprestimo;
	private JMenu mnSistema;
	private JMenuItem mntmSair;
	private JMenuItem mntmCliente;
	private JMenuItem mntmSala;
	private JMenu mnConsulta;
	private JMenuItem mntmClienteConsulta;
	private JMenuItem mntmSalaConsulta;
	private JMenuItem mntmEmprestimo;

	/**
	 * Create the frame.
	 */
	public FramePrincipal() {
		setTitle("Chaveiro do IFMA");
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);

		mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		btnEmprestimo = new JButton("Empr\u00E9stimo");
		btnEmprestimo.addActionListener(this);
		
		mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);
		
		mntmClienteConsulta = new JMenuItem("Cliente");
		mnConsulta.add(mntmClienteConsulta);
		
		mntmSalaConsulta = new JMenuItem("Sala");
		mnConsulta.add(mntmSalaConsulta);
		
		mntmEmprestimo = new JMenuItem("Empr\u00E9stimo");
		mnConsulta.add(mntmEmprestimo);
		menuBar.add(btnEmprestimo);

		mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(this);
		mnSistema.add(mntmSair);

		mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(this);
		mnCadastro.add(mntmCliente);

		mntmSala = new JMenuItem("Sala");
		mntmSala.addActionListener(this);
		mnCadastro.add(mntmSala);
		
		this.add(desktop);
		
//		Image icone = this.getToolkit().getImage(getClass().getResource("/imagens/keys - fundo.png"));
//        this.setIconImage(icone);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(400, 300);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnEmprestimo) {
			
			TelaEmprestimo.getInstance().exibir(desktop);
		}

		else if (e.getSource() == mntmCliente) {
			
		}

		else if (e.getSource() == mntmSala) {
			
		}

		else if (e.getSource() == mntmSair) {
			System.exit(0);
		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao inicializar Look and Feel ...");
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteDAO.getInstance(); //FIXME mudar essa forma de inicializar o hibernate
					new FramePrincipal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
