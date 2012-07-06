package telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import relatorios.RelatorioEmprestimo;
import util.MaxLengthDocument;
import util.MyModelTable;
import controles.ClienteControle;
import controles.EmprestimoControle;
import controles.SalaControle;
import entidade.Cliente;
import entidade.Sala;
import excecoes.SistemaException;
import javax.swing.ImageIcon;

public class TelaRelatorio extends MyInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1890373034466129618L;

	private static TelaRelatorio tela;
	
	private EmprestimoControle emprestimoControle = EmprestimoControle.getInstance();
	private ClienteControle clienteControle = ClienteControle.getInstance();
	private SalaControle salaControle = SalaControle.getInstance();
	
	private JPanel painelCliente, painelSala;
	private JTabbedPane painelTabbed;

	private MyModelTable modelTableCliente, modelTableSala;
	private JTextField tfNomeCliente;
	private JTextField tfNomeSala;

	private JButton btnRelatorioCliente, btnRelatorioSala;
	private JLabel lblErroCliente, lblErroSala;

	public TelaRelatorio() {
		super("Gera\u00E7\u00E3o de relat\u00F3rios", "/imagens/mnrelatorios.png");

		painelTabbed = new JTabbedPane();
		setContentPane(painelTabbed);

		painelCliente = new JPanel();
		painelCliente.setLayout(null);
		painelTabbed.add("Clientes", painelCliente);

		painelSala = new JPanel();
		painelSala.setLayout(null);
		painelTabbed.add("Salas", painelSala);

		JScrollPane scrollCliente = new JScrollPane();
		scrollCliente.setBounds(25, 74, 358, 281);

		JTable tabelaCliente = new JTable();
		tabelaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelTableCliente = new MyModelTable("Nome", "Tipo de perfil");
		modelTableCliente.setTable(tabelaCliente);
		scrollCliente.setViewportView(tabelaCliente);
		painelCliente.add(scrollCliente);

		JLabel lblNomeCliente = new JLabel("Nome do cliente:");
		lblNomeCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeCliente.setBounds(25, 29, 124, 14);
		painelCliente.add(lblNomeCliente);

		lblErroCliente = new JLabel("");
		lblErroCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroCliente.setForeground(Color.RED);
		lblErroCliente.setBounds(159, 48, 166, 16);
		painelCliente.add(lblErroCliente);

		tfNomeCliente = new JTextField(new MaxLengthDocument(Cliente.TAMANHO_MAX_NOME), "", 10);
		tfNomeCliente.setBounds(159, 22, 166, 28);
		tfNomeCliente.addKeyListener(new OuvinteTextField());
		painelCliente.add(tfNomeCliente);

		btnRelatorioCliente = new JButton("Gerar relat\u00F3rio");
		btnRelatorioCliente.setIcon(new ImageIcon(TelaRelatorio.class.getResource("/imagens/btreport.png")));
		btnRelatorioCliente.setBounds(134, 367, 145, 30);
		btnRelatorioCliente.addActionListener(this);
		painelCliente.add(btnRelatorioCliente);

		// ---------------------------------------------------

		JScrollPane scrollSala = new JScrollPane();
		scrollSala.setBounds(25, 74, 358, 281);

		JTable tabelaSala = new JTable();
		tabelaSala.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelTableSala = new MyModelTable("Nome", "Status");
		modelTableSala.setTable(tabelaSala);
		scrollSala.setViewportView(tabelaSala);
		painelSala.add(scrollSala);

		JLabel lblNomeSala = new JLabel("Nome da sala:");
		lblNomeSala.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeSala.setBounds(25, 29, 124, 14);
		painelSala.add(lblNomeSala);

		lblErroSala = new JLabel("");
		lblErroSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroSala.setForeground(Color.RED);
		lblErroSala.setBounds(159, 48, 166, 16);
		painelSala.add(lblErroSala);

		tfNomeSala = new JTextField();
		tfNomeSala.setBounds(159, 22, 166, 28);
		tfNomeSala.addKeyListener(new OuvinteTextField());
		painelSala.add(tfNomeSala);

		btnRelatorioSala = new JButton("Gerar relat\u00F3rio");
		btnRelatorioSala.setIcon(new ImageIcon(TelaRelatorio.class.getResource("/imagens/btreport.png")));
		btnRelatorioSala.setBounds(134, 367, 145, 30);
		btnRelatorioSala.addActionListener(this);
		painelSala.add(btnRelatorioSala);

		//
		this.setSize(419, 471);
		utilidades.centralizaJanela(this, 40);

		carregarClientes("");
		carregarSalas("");
		this.setVisible(true);
	}
	
	public static TelaRelatorio getInstance() {
		if(tela == null){
			tela = new TelaRelatorio();
		}
		return tela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		//TODO gerar relatorios por periodo
		
		if (e.getSource() == btnRelatorioCliente) {
			
			try {

				Cliente cliente = (Cliente) modelTableCliente.getSelectedKey();
				
				new RelatorioEmprestimo().openPdf(emprestimoControle.getEmprestimos(cliente), cliente.getNome(), RelatorioEmprestimo.TIPO_CLIENTE);
				
			} catch (SistemaException ex) {
				utilidades.msgError("Erro ao tentar gerar relatório");
			}
			
		}

		else if (e.getSource() == btnRelatorioSala) {
			try {

				Sala sala = (Sala) modelTableSala.getSelectedKey();
				
				new RelatorioEmprestimo().openPdf(emprestimoControle.getEmprestimos(sala), sala.getNome(), RelatorioEmprestimo.TIPO_SALA);
				
			} catch (SistemaException ex) {
				utilidades.msgError("Erro ao tentar gerar relatório");
			}
		}

	}

	private class OuvinteTextField implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {

			if (e.getSource() == tfNomeCliente) {
				carregarClientes(tfNomeCliente.getText());
			} else if (e.getSource() == tfNomeSala) {
				carregarSalas(tfNomeSala.getText());
			}

		}

		@Override
		public void keyTyped(KeyEvent e) { }

		@Override
		public void keyPressed(KeyEvent e) { }

	}

	private void carregarClientes(String nome) {

		try {

			modelTableCliente.clear();

			List<Cliente> list = clienteControle.consultaPorNome(nome);
			for (Cliente c : list) {
				modelTableCliente.addRowAndKey(c, c.getNome(), c.getPerfilStr());
			}

			lblErroCliente.setText("");

		} catch (SistemaException e) {
			lblErroCliente.setText(e.getMessage());
		} finally {
			modelTableCliente.updateUI();
		}

	}

	private void carregarSalas(String nome) {

		try {

			modelTableSala.clear();

			List<Sala> list = salaControle.consultaPorNome(nome);
			for (Sala s : list) {
				modelTableSala.addRowAndKey(s, s.getNome(), s.getStatusStr());
			}

			lblErroSala.setText("");

		} catch (SistemaException e) {
			lblErroSala.setText(e.getMessage());
		} finally {
			modelTableSala.updateUI();
		}

	}

}
