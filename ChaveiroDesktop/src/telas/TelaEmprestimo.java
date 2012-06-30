package telas;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import dao.SalaDAO;
import entidade.Sala;

public class TelaEmprestimo extends MyInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private SalaDAO salaDAO = SalaDAO.getInstance();
	private List<Sala> listSala = salaDAO.consultar();
	
	/**
	 * Create the frame.
	 */
	public TelaEmprestimo() {
		super("Empréstimo de chaves");
		
		setBounds(100, 100, 443, 402);
		getContentPane().setLayout(new GridLayout(3, 4, 2, 2));
		
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
	private void carregarSalas(){
		
		JButton b;
		for(Sala s : listSala){
			
			ImageIcon img;
			if(s.getStatus() == true){
				img = utilidades.imgBtSalaAberta;
			}
			else{
				img = utilidades.imgBtSalaFechada;
			}
			
			String texto = s.getCodigo()+"\nCliente: "+s.getUltimoCliente().getNome()+"\nData retirada: "+s.getUltimoEmprestimo().getDataRetirada();
			
			b = new JButton(texto, img);
			b.setName(""+s.getIdsala());
			b.addActionListener(this);
			
			b.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			b.setVerticalAlignment(javax.swing.SwingConstants.TOP);
			b.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
			
			this.add(b);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		
		int idSala = Integer.parseInt(b.getName());
		
		Sala s = getSala(idSala);
		
		System.out.println(s.getUltimoCliente());
		
		System.out.println("idSala do botão clicado: " + idSala);
		
	}
	
	private void mostrarStatusSala(){
		
		
		
	}
	
	private Sala getSala(int id){
		
		for(Sala s : listSala){
			if(id == s.getIdsala()){
				return s;
			}
		}
		
		return null;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEmprestimo frame = new TelaEmprestimo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
