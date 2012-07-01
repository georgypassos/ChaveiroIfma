package util;

import entidade.Cliente;


public class ConstantesComboBox {
	
	private static final SelectItem tipoPerfil[] = {
        new SelectItem(Cliente.PERFIL_USUARIO, "Usuário"),
        new SelectItem(Cliente.PERFIL_ADMINISTRADOR, "Administrador"),
        new SelectItem(Cliente.PERFIL_CLIENTE, "Cliente")
    };
	
	public static final MyModelComboBox modelPerfil = new MyModelComboBox(tipoPerfil);
	
}
