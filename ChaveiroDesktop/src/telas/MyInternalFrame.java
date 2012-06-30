package telas;

import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;

import util.Utilidades;

public abstract class MyInternalFrame extends JInternalFrame{

	private static final long serialVersionUID = -6247889178970184108L;

    protected Utilidades utilidades = Utilidades.getInstance();
	
	protected static MyInternalFrame tela = null;
    private boolean natela = false;
    
    protected MyInternalFrame(String title){
    	super(title, false, true, false, true);
    	
    	this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {

            @Override
            public void internalFrameClosed(InternalFrameEvent arg0) {
                natela = false;
                tela = null;
            }
        });
    	
    }
    
    protected void exibir(JDesktopPane desktop) {

        if (!tela.natela) {
            desktop.add(tela);
            tela.natela = true;
        }
        
        try {
        	tela.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }
    
}
