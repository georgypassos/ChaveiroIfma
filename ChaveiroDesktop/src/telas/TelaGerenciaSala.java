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
	
	private JPanel painelCadastro;
	private JTextField tfcodigoSala;
	private JLabel lblASalaEst;
	private JCheckBox cbManutencao;
	
	JButton btnSalvar, btnCancelarSala;
	
	private SalaDAO saladao; 
	private Sala sala;
	private JButton btnNovo;

	/**
	 * Create the frame.
	 */
	public TelaGerenciaSala() {
		super("Cadastro de Salas");
		
		painelCadastro = new JPanel();
		painelCadastro.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelCadastro);
		
		tfcodigoSala = new JTextField();
		tfcodigoSala.setBounds(171, 54, 157, 20);
		tfcodigoSala.setColumns(10);
		
		JLabel lbCodigoSala = new JLabel("C\u00F3digo da Sala:");
		lbCodigoSala.setBounds(59, 57, 108, 15);
		lbCodigoSala.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblASalaEst = new JLabel("A sala esta atualmente em manutenção?");
		lblASalaEst.setBounds(59, 116, 331, 15);
		
		cbManutencao = new JCheckBox("");
		cbManutencao.setBounds(59, 138, 129, 23);
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
		painelCadastro.add(lblASalaEst);
		painelCadastro.add(cbManutencao);
		painelCadastro.add(btnSalvar);
		painelCadastro.add(btnCancelarSala);
		painelCadastro.add(btnNovo);
		
		this.saladao = SalaDAO.getInstance();
		this.sala = new Sala();
		
		this.setBounds(100, 100, 455, 304);
		utilidades.formataJanela(this, "/imagens/sala.png");
		this.setVisible(true);
	}
	
	public static MyInternalFrame getInstance() {
		
		if (tela == null) {
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
			sala.setCodigo(tfcodigoSala.getText());
			if(this.cbManutencao.isSelected()){
				sala.setStatus(Sala.STATUS_INDISPONIVEL);
			}
			saladao.inserir(sala);
			JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void cancelar(){
		limparCampos();
		this.dispose();
	}
}

	
	
	
	

