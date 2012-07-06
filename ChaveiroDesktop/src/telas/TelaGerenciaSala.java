package telas;

import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import util.ConstantesComboBox;
import util.MyModelTable;
import controles.SalaControle;
import entidade.Sala;
import excecoes.SistemaException;

public class TelaGerenciaSala extends MyInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private static TelaGerenciaSala tela;
	
	private JPanel painelCadastro, painelConsulta;
	private JTabbedPane painelTabbed;
	
	private JTextField tfNomeSala;
	
	private JButton btnNovo, btnSalvar, btnCancelar, btnExcluir, btnEditar;

	private JComboBox cbStatus;
	
	private MyModelTable modelTableConsulta;
	private JTextField tfNomeConsulta;
	private JLabel lblErroConsulta;

	private Sala sala;
	private SalaControle salaControle = SalaControle.getInstance(); 
	
	/**
	 * Create the frame.
	 */
	public TelaGerenciaSala() {
		super("Cadastro de Salas", "/imagens/mnsalas.png");
		
		painelTabbed = new JTabbedPane();
		setContentPane(painelTabbed);
		
		painelCadastro = new JPanel();
		painelCadastro.setLayout(null);
		painelTabbed.add("Cadastro", painelCadastro);
		
		painelConsulta = new JPanel();
		painelConsulta.setLayout(null);
		painelTabbed.add("Consulta", painelConsulta);

		// --- painel de cadastro
		
		tfNomeSala = new JTextField();
		tfNomeSala.setBounds(171, 54, 157, 28);
		tfNomeSala.setColumns(10);
		
		JLabel lbCodigoSala = new JLabel("C\u00F3digo da Sala:");
		lbCodigoSala.setBounds(59, 61, 108, 15);
		lbCodigoSala.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cbStatus = new JComboBox();
		cbStatus.setBounds(171, 93, 157, 28);
		cbStatus.setModel(ConstantesComboBox.modelStatusSala);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(135, 178, 95, 30);
		btnSalvar.setIcon(new ImageIcon(TelaGerenciaSala.class.getResource("/imagens/btsave.png")));
		btnSalvar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(248, 178, 113, 30);
		btnCancelar.setIcon(new ImageIcon(TelaGerenciaSala.class.getResource("/imagens/btcancel.png")));
		btnCancelar.addActionListener(this);
		
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(24, 177, 93, 30);
		btnNovo.setIcon(new ImageIcon(TelaGerenciaSala.class.getResource("/imagens/btnew.png")));
		painelCadastro.setLayout(null);
		painelCadastro.add(tfNomeSala);
		painelCadastro.add(lbCodigoSala);
		painelCadastro.add(cbStatus);
		painelCadastro.add(btnSalvar);
		painelCadastro.add(btnCancelar);
		painelCadastro.add(btnNovo);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setBounds(121, 97, 46, 14);
		painelCadastro.add(lblStatus);
		
		// ---- painel de consulta

		JScrollPane scrollConsulta = new JScrollPane();
		scrollConsulta.setBounds(10, 85, 364, 220);
		
		JTable tabelaConsulta = new JTable();
		tabelaConsulta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelTableConsulta = new MyModelTable("Nome", "Status");
		modelTableConsulta.setTable(tabelaConsulta);
		scrollConsulta.setViewportView(tabelaConsulta);
		painelConsulta.add(scrollConsulta);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(76, 28, 46, 14);
		painelConsulta.add(lblNome);
		
		tfNomeConsulta = new JTextField();
		tfNomeConsulta.setBounds(132, 21, 170, 28);
		tfNomeConsulta.addKeyListener(new OuvinteConsulta());
		painelConsulta.add(tfNomeConsulta);

		lblErroConsulta = new JLabel("");
		lblErroConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroConsulta.setForeground(Color.RED);
		lblErroConsulta.setBounds(132, 54, 170, 20);
		painelConsulta.add(lblErroConsulta);
		
		btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(TelaGerenciaSala.class.getResource("/imagens/btedit.png")));
		btnEditar.setBounds(76, 326, 102, 30);
		btnEditar.addActionListener(this);
		painelConsulta.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(TelaGerenciaSala.class.getResource("/imagens/btdelete.png")));
		btnExcluir.setBounds(213, 326, 102, 30);
		btnExcluir.addActionListener(this);
		painelConsulta.add(btnExcluir);
		
		this.setSize(407, 432);
	}

	@Override
	protected void initialize() {
		carregarTabela("");
		sala = new Sala();
		utilidades.centralizaJanela(this, 40);
	}
	
	public static TelaGerenciaSala getInstance() {
		if(tela == null){
			tela = new TelaGerenciaSala();
		}
		return tela;
	}

	private class OuvinteConsulta implements KeyListener{

		@Override
		public void keyReleased(KeyEvent e) {
			carregarTabela(tfNomeConsulta.getText());
		}
		
		@Override
		public void keyTyped(KeyEvent e) { }

		@Override
		public void keyPressed(KeyEvent e) { }
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnSalvar){
			sala = salaControle.salvar(this);
			carregarTabela("");
			TelaEmprestimo.getInstance().initialize();
		}
		
		else if(e.getSource() == btnCancelar){
			cancelar();
		}
		
		else if(e.getSource() == btnNovo){
			novo();
		}
		else if(e.getSource() == btnExcluir){
			salaControle.excluir(this);
			carregarTabela("");
		}
		else if(e.getSource() == btnEditar){
			sala = (Sala) modelTableConsulta.getSelectedKey();
			setInfoSala(sala);
			painelTabbed.setSelectedIndex(0);
		}
		
	}

	private void novo(){
		sala = new Sala();
		limparCampos();
	}

	private void cancelar(){
		this.dispose();
	}
	
	private void carregarTabela(String nome){
		
		try {
			
			modelTableConsulta.clear();
			
			List<Sala> list = salaControle.consultaPorNome(nome);
			for(Sala s : list){
				modelTableConsulta.addRowAndKey(s, s.getNome(), s.getStatusStr());
			}
			
			lblErroConsulta.setText("");
			
		} catch (SistemaException e) {
			
			lblErroConsulta.setText(e.getMessage());
		}
		finally{
			modelTableConsulta.updateUI();
		}
		
	}

	private void setInfoSala(Sala sala){
		tfNomeSala.setText(sala.getNome());
		cbStatus.setSelectedIndex(sala.getStatus());
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

	public Sala getSala() {
		return sala;
	}

	public JTextField getTfNomeSala() {
		return tfNomeSala;
	}

	public MyModelTable getModelTableConsulta() {
		return modelTableConsulta;
	}

	public JComboBox getCbStatus() {
		return cbStatus;
	}
}

	
	
	
	

