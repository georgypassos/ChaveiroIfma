package telas;

import java.awt.Color;
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

import util.MaxLengthDocument;
import util.MyModelTable;
import controles.ClienteControle;
import controles.SalaControle;
import entidade.Cliente;
import excecoes.SistemaException;

public class TelaRelatorio extends MyInternalFrame {

	private static final long serialVersionUID = 1890373034466129618L;
	
	private JPanel painelCliente, painelSala;
	private JTabbedPane painelTabbed;
	
	private MyModelTable modelTableCliente, modelTableSala;
	private JTextField tfNomeCliente;
	private JTextField tfNomeSala;
	
	private JButton btnRelatorioCliente, btnRelatorioSala;
	
	private ClienteControle clienteControle = ClienteControle.getInstance();
	private SalaControle salaControle = SalaControle.getInstance();
	private JLabel lblErroCliente;
	
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
		scrollCliente.setBounds(25, 86, 358, 269);
		
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
		lblErroCliente.setBounds(159, 58, 166, 16);
		painelCliente.add(lblErroCliente);
		
		tfNomeCliente = new JTextField(new MaxLengthDocument(Cliente.TAMANHO_MAX_NOME), "", 10);
		tfNomeCliente.setBounds(159, 22, 166, 28);
		tfNomeCliente.addKeyListener(new OuvinteTextField());
		painelCliente.add(tfNomeCliente);
		
		btnRelatorioCliente = new JButton("Gerar relat\u00F3rio");
		btnRelatorioCliente.setBounds(134, 367, 145, 30);
		painelCliente.add(btnRelatorioCliente);
		
		// ---------------------------------------------------

		JScrollPane scrollSala = new JScrollPane();
		scrollSala.setBounds(25, 62, 358, 269);
		
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
		
		tfNomeSala = new JTextField();
		tfNomeSala.setBounds(159, 22, 166, 28);
		tfNomeSala.addKeyListener(new OuvinteTextField());
		painelSala.add(tfNomeSala);

		btnRelatorioSala = new JButton("Gerar relat\u00F3rio");
		btnRelatorioSala.setBounds(134, 343, 145, 30);
		painelSala.add(btnRelatorioSala);
		
		//
		this.setSize(419, 471);
		utilidades.centralizaJanela(this, 40);
		
		carregarClientes("");
//		carregarSalas("");
		this.setVisible(true);
	}
	
	private class OuvinteTextField implements KeyListener{

		@Override
		public void keyReleased(KeyEvent e) {
			
			if(e.getSource() == tfNomeCliente){
				carregarClientes(tfNomeCliente.getText());
			}
			else if(e.getSource() == tfNomeSala){
				
			}
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) { }

		@Override
		public void keyPressed(KeyEvent e) { }

	}

	private void carregarClientes(String nome){
		
		try {
			
			modelTableCliente.clear();
			
			List<Cliente> list = clienteControle.consultaPorNome(nome);
			for(Cliente c : list){
				modelTableCliente.addRowAndKey(c, c.getNome(), c.getPerfilStr());
			}
			
//			lblErroConsulta.setText("");
			
		} catch (SistemaException e) {
			
//			lblErroConsulta.setText(e.getMessage());
		}
		finally{
			modelTableCliente.updateUI();
		}
		
	}
	
}