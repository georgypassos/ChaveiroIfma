package telas;

import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import util.Utilidades;

public abstract class MyInternalFrame extends JInternalFrame{

	private static final long serialVersionUID = -6247889178970184108L;

    protected Utilidades utilidades = Utilidades.getInstance();
    
    protected boolean init = false;
    
    protected MyInternalFrame(String title, String srcImagem){
    	super(title, false, true, false, true);
    	
    	utilidades.formataJanela(this, srcImagem);
    }

	public void show(JDesktopPane desktopPane){
		
		if (!init) {
			init = true;
			
			desktopPane.add(this);
			this.setVisible(true);
		}

		desktopPane.moveToFront(this);
		
		try {
			this.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

}
