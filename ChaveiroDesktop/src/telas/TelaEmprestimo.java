package telas;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
			b = new JButton(s.getCodigo());
			b.setName(""+s.getIdsala());
			b.addActionListener(this);
			
			this.add(b);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		
		int idSala = Integer.parseInt(b.getName());
		
		salaDAO.get(idSala);
		
		System.out.println("idSala do botão clicado: " + idSala);
		
	}
	
	private Sala getSala(){
		
		for(Sala s : listSala){
			
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
