package telas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.SalaDAO;
import entidade.Sala;

public class TelaGerenciaSala extends MyInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private static TelaGerenciaSala tela;
	
	private JPanel painelCadastro;
	private JTextField tfcodigoSala;
	private JCheckBox cbManutencao;
	
	JButton btnSalvar, btnCancelarSala;
	
	private SalaDAO saladao; 
	private Sala sala;
	private JButton btnNovo;

	/**
	 * Create the frame.
	 */
	public TelaGerenciaSala() {
		super("Cadastro de Salas", "/imagens/mnsala.png");
		
		painelCadastro = new JPanel();
		painelCadastro.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelCadastro);
		
		tfcodigoSala = new JTextField();
		tfcodigoSala.setBounds(171, 54, 157, 28);
		tfcodigoSala.setColumns(10);
		
		JLabel lbCodigoSala = new JLabel("C\u00F3digo da Sala:");
		lbCodigoSala.setBounds(59, 61, 108, 15);
		lbCodigoSala.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cbManutencao = new JCheckBox("A sala est\u00E1 atualmente indispon\u00EDvel?");
		cbManutencao.setBounds(77, 115, 281, 23);
		cbManutencao.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(160, 202, 95, 30);
		btnSalvar.setIcon(new ImageIcon(TelaGerenciaSala.class.getResource("/imagens/btsave.png")));
		
		btnSalvar.addActionListener(this);
		
		btnCancelarSala = new JButton("Cancelar");
		btnCancelarSala.setBounds(273, 202, 113, 30);
		btnCancelarSala.setIcon(new ImageIcon(TelaGerenciaSala.class.getResource("/imagens/btcancel.png")));
		
		btnCancelarSala.addActionListener(this);
		
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(49, 201, 93, 30);
		btnNovo.setIcon(new ImageIcon(TelaGerenciaSala.class.getResource("/imagens/btnew.png")));
		painelCadastro.setLayout(null);
		painelCadastro.add(tfcodigoSala);
		painelCadastro.add(lbCodigoSala);
		painelCadastro.add(cbManutencao);
		painelCadastro.add(btnSalvar);
		painelCadastro.add(btnCancelarSala);
		painelCadastro.add(btnNovo);
		
		this.saladao = SalaDAO.getInstance();
		this.sala = new Sala();
		
		this.setSize(447, 282);
		utilidades.centralizaJanela(this, 40);
		this.setVisible(true);
	}

	public static TelaGerenciaSala getInstance() {
		if(tela == null){
			tela = new TelaGerenciaSala();
		}
		return tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnSalvar){
			cadastrar();
		}
		
		else if(e.getSource() == btnCancelarSala){
			cancelar();
		}
		
		else if(e.getSource() == btnNovo){
			limparCampos();
		}
		
	}
	
	private void limparCampos(){
		sala = new Sala();
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
	
	private void cadastrar(){
		try{
			sala.setNome(tfcodigoSala.getText());
			if(this.cbManutencao.isSelected()){
				sala.setStatus(Sala.STATUS_INDISPONIVEL);
			}
			saladao.inserir(sala);
			JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
		}catch (Exception e) {
			
		}
	}
	
	private void cancelar(){
		limparCampos();
		this.dispose();
	}
}

	
	
	
	

