package telas;

import java.awt.BorderLayout;
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
	
	private JPanel painelEmprestimo;
	private SalaDAO salaDAO = SalaDAO.getInstance();
	private List<Sala> listSala;
//	private List<Sala> listSala = new ArrayList<Sala>();
	
	/**
	 * Create the frame.
	 */
	public TelaEmprestimo() {
		super("Empréstimo de chaves");
		
		painelEmprestimo = new JPanel();
		painelEmprestimo.setLayout(new GridLayout(3, 4, 2, 2));
		this.setContentPane(painelEmprestimo);
		
		this.setBounds(100, 100, 457, 444);
		utilidades.formataJanela(this, "/imagens/imgchave.png");
		
		carregarSalas();
		this.setVisible(true);
	}

	public static MyInternalFrame getInstance() {
		
		if (tela == null) {
            tela = new TelaEmprestimo();
        }
		
        return tela;
	}

	//FIXME fazer esse método mais variável
	public void carregarSalas(){
		
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
				
				String texto = s.getCodigo();
				
				
				
				try {
					texto += "<br><br><b>Cliente:</b> "+s.getUltimoCliente().getNome()+
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		
		int idSala = Integer.parseInt(b.getName());
		
		Sala s = getSala(idSala);
		
		if(s.getUltimoEmprestimo() == null){
			
			new DialogEmprestimo(s);
			carregarSalas();
		}
		else{
			
//			new DialogDevolucao(s);
			carregarSalas();
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
