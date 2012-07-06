package telas;

import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;

import util.Utilidades;

public abstract class MyInternalFrame extends JInternalFrame{

	private static final long serialVersionUID = -6247889178970184108L;

    protected Utilidades utilidades = Utilidades.getInstance();
    
    private boolean init = false;
    
    protected MyInternalFrame(String title, String srcImagem){
    	super(title, false, true, false, true);
    	
    	utilidades.formataJanela(this, srcImagem);
    	
    	this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {

            @Override
            public void internalFrameClosed(InternalFrameEvent arg0) {
                init = false;
            }
        });
    }

	public void show(JDesktopPane desktopPane){
		if (!init) {
			init = true;
			
			desktopPane.add(this);
			this.setVisible(true);
			
			initialize();
		}

		desktopPane.moveToFront(this);
		
		try {
			this.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	protected abstract void initialize();
	
}
