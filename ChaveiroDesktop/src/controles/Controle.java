package controles;

import util.Utilidades;

public abstract class Controle {
	
	protected Utilidades utilidades = Utilidades.getInstance();
	
	protected Object view;
	
	protected Controle(Object view){
		this.view = view;
	}
	
	
}
