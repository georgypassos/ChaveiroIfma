package util;

import entidade.Cliente;
import entidade.Sala;


public class ConstantesComboBox {

	private static final SelectItem tipoPerfil[] = {
        new SelectItem(Cliente.PERFIL_USUARIO, "Usuário"),
        new SelectItem(Cliente.PERFIL_ADMINISTRADOR, "Administrador"),
        new SelectItem(Cliente.PERFIL_CLIENTE, "Cliente")
    };
	
	public static final MyModelComboBox modelPerfil = new MyModelComboBox(tipoPerfil);

	private static final SelectItem statusSala[] = {
        new SelectItem(Sala.STATUS_FECHADA, "Fechada"),
        new SelectItem(Sala.STATUS_ABERTA, "Aberta"),
        new SelectItem(Sala.STATUS_INDISPONIVEL, "Indisponível")
    };
	
	public static final MyModelComboBox modelStatusSala = new MyModelComboBox(statusSala);
	
}
