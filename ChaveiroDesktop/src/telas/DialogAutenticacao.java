package telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidade.Emprestimo;

public class DialogAutenticacao extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private Emprestimo emprestimo;
	
	
	/**
	 * Create the dialog.
	 */
	public DialogAutenticacao(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setVisible(true);
	}

}
