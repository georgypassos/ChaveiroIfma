package telas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.SalaDAO;
import entidade.Sala;

public class TelaEmprestimo extends MyInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private static TelaEmprestimo tela;
	
	private JPanel painelEmprestimo;
	private SalaDAO salaDAO = SalaDAO.getInstance();
	private List<Sala> listSala;
	
	private TelaEmprestimo() {
		super("Empréstimo de chaves", "/imagens/imgchave.png");
		
		painelEmprestimo = new JPanel();
		
		this.setSize(458, 444);
		this.setMaximumSize(new Dimension(458, 600));
	}

	@Override
	protected void initialize() {
		carregarSalas();
		utilidades.centralizaJanela(this, 40);
	}
	
	public static TelaEmprestimo getInstance() {
		if(tela == null){
			tela = new TelaEmprestimo();
		}
		return tela;
	}
	
	//FIXME fazer esse método mais variável (adaptável quando o numero de salas aumentar)
	public void carregarSalas(){
		
		atualizaPainel();
		
		listSala = salaDAO.consultar();
		
		if(listSala != null && listSala.size() > 0){
			
			JButton b;
			for(Sala s : listSala){
				
				ImageIcon img = null;
				if(s.getStatus() == Sala.STATUS_ABERTA){
					img = utilidades.imgBtSalaAberta;
				}
				else if(s.getStatus() == Sala.STATUS_FECHADA){
					img = utilidades.imgBtSalaFechada;
				}
				else if(s.getStatus() == Sala.STATUS_INDISPONIVEL){
					img = utilidades.imgBtSalaIndisponivel;
				}
				
				String texto = s.getNome();
				
				try {
					texto += "<br><br><b>"+s.getUltimoCliente().getNome()+"</b>"+
							 "<br><b>Retirada:</b> "+utilidades.getData(s.getUltimoEmprestimo().getDataRetirada(), "dd/MM HH:mm");
					texto = utilidades.getHtml(texto);
				} catch (Exception e) { }
				
				b = new JButton(texto, img);
				b.setName(""+s.getIdsala());
				b.addActionListener(this);
				
				b.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				b.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
				b.setVerticalAlignment(javax.swing.SwingConstants.TOP);
				b.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
				
				painelEmprestimo.add(b);
			}
			
		}
		else{
			
			painelEmprestimo.setLayout(new BorderLayout());
			
			JLabel lblErro = new JLabel("Não há salas cadastradas!");
			lblErro.setFont(new Font("Tahoma", Font.BOLD, 16));
			painelEmprestimo.add(lblErro, BorderLayout.CENTER);
			
		}
		
	}
	
	private void atualizaPainel(){
		painelEmprestimo = null;
		painelEmprestimo = new JPanel();
		painelEmprestimo.setLayout(new GridLayout(3, 4, 2, 2));
		this.setContentPane(painelEmprestimo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		
		//pega o NAME do botao que tem o ID da sala
		int idSala = Integer.parseInt(b.getName());
		Sala s = getSala(idSala);
		
		if(s.getStatus() == Sala.STATUS_FECHADA){
			
			new DialogEmprestimo(s);
			
			carregarSalas();
		}
		else if(s.getStatus() == Sala.STATUS_ABERTA){
			
			new DialogDevolucao(s.getUltimoEmprestimo());
			 
			carregarSalas();
		}
		else if(s.getStatus() == Sala.STATUS_INDISPONIVEL){
			
			utilidades.msgWarning("Sala indisponível no momento!");
		}
		
		
	}
	
	private Sala getSala(int id){
		
		for(Sala s : listSala){
			if(id == s.getIdsala()){
				return s;
			}
		}
		
		return null;
	}
	

}
