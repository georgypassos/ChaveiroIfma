package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import util.Utilidades;
import entidade.Cliente;

public class FramePrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static FramePrincipal framePrincipal;
	
	private Utilidades utilidades = Utilidades.getInstance();
	
	/**
	 * @wbp.nonvisual location=80,339
	 */
	protected final JDesktopPane desktop = new JDesktopPane();

	private JMenu mnGerencia;
	private JButton btnEmprestimo, btnRelatorios;
	private JMenu mnSistema;
	private JMenuItem mntmSair;
	private JMenuItem mntmCliente;
	private JMenuItem mntmSala;
	
	private Cliente usuarioLogado;

	/**
	 * Create the frame.
	 */
	private FramePrincipal() {
		setTitle("Chaveiro do IFMA");
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);

		mnGerencia = new JMenu("Gerenciar");
		menuBar.add(mnGerencia);

		btnEmprestimo = new JButton("Empr\u00E9stimo de Chaves");
		btnEmprestimo.setIcon(new ImageIcon(FramePrincipal.class.getResource("/imagens/mnchaves.png")));
		btnEmprestimo.addActionListener(this);
		menuBar.add(btnEmprestimo);

		mntmSair = new JMenuItem("Sair");
		mntmSair.setIcon(new ImageIcon(FramePrincipal.class.getResource("/imagens/mnsignout.png")));
		mntmSair.addActionListener(this);
		mnSistema.add(mntmSair);

		mntmCliente = new JMenuItem("Clientes");
		mntmCliente.setIcon(new ImageIcon(FramePrincipal.class.getResource("/imagens/mnusuarios.png")));
		mntmCliente.addActionListener(this);
		mnGerencia.add(mntmCliente);

		mntmSala = new JMenuItem("Salas");
		mntmSala.setIcon(new ImageIcon(FramePrincipal.class.getResource("/imagens/mnsalas.png")));
		mntmSala.addActionListener(this);
		mnGerencia.add(mntmSala);
		
		btnRelatorios = new JButton("Relat\u00F3rios");
		btnRelatorios.setIcon(new ImageIcon(FramePrincipal.class.getResource("/imagens/mnrelatorios.png")));
		btnRelatorios.addActionListener(this);
		menuBar.add(btnRelatorios);
		
		getContentPane().add(desktop);
		
//		Image icone = this.getToolkit().getImage(getClass().getResource("/imagens/keys - fundo.png"));
//        this.setIconImage(icone);
		
		desktop.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(400, 300);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setPermissoes();
	}
	
	public static FramePrincipal getInstance(){
		if(framePrincipal == null){
			framePrincipal = new FramePrincipal();
		}
		
		return framePrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnEmprestimo) {
			TelaEmprestimo.getInstance().show(desktop);
		}
		else if (e.getSource() == btnRelatorios) {
			TelaRelatorio.getInstance().show(desktop);
		}
		else if (e.getSource() == mntmCliente) {
			TelaGerenciaCliente.getInstance().show(desktop);
		}
		else if (e.getSource() == mntmSala) {
			TelaGerenciaSala.getInstance().show(desktop);
		}
		else if (e.getSource() == mntmSair) {
			int opcao = utilidades.getYesNoOption("Deseja realmente fechar o sistema?");
			
			if(opcao == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}

	}
	
	private void setPermissoes(){
		
		if(usuarioLogado != null){
			if(usuarioLogado.getPerfil() == Cliente.PERFIL_USUARIO){
				mntmCliente.setEnabled(false);
				mntmSala.setEnabled(false);
			}
		}
		
	}

	public Cliente getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Cliente usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
