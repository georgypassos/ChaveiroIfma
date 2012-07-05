package telas;

import javax.swing.JDialog;

import util.Utilidades;

public class MyDialog extends JDialog{

    private static final long serialVersionUID = -6063134467138042973L;
	
	protected Utilidades utilidades = Utilidades.getInstance();
	
	protected MyDialog(String title){
		this.setTitle(title);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		utilidades.centralizaJanela(this, 80);
	}
	
}
