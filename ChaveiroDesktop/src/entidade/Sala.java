package entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sala implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idsala;

	private String codigo;

	private Integer status;

	@OneToMany(mappedBy = "sala")
	private List<Emprestimo> listEmprestimo;

	public static final int STATUS_FECHADA = 0;
	public static final int STATUS_ABERTA = 1;
	public static final int STATUS_INDISPONIVEL = 2;

	public Sala() {
		super();
	}

	public Integer getIdsala() {
		return idsala;
	}

	public void setIdsala(Integer idsala) {
		this.idsala = idsala;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getStatus() {
		return status;
	}

	public String getStatusStr() {

		if (status != null) {
			if (status == STATUS_ABERTA)
				return "Aberta";
			if (status == STATUS_FECHADA)
				return "Fechada";
			if (status == STATUS_INDISPONIVEL)
				return "Indisponivel";
		}

		return "";
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Emprestimo> getListEmprestimo() {
		return listEmprestimo;
	}

	//Retorna o ultimo emprestimo aberto
	public Emprestimo getUltimoEmprestimo() {

		if (listEmprestimo != null && listEmprestimo.size() > 0) {
			
			Emprestimo e = listEmprestimo.get(listEmprestimo.size() - 1);
			
			if(e.getDataEntrega() == null){
				return e;
			}
			
		}

		return null;
	}

	public Cliente getUltimoCliente() {

		Emprestimo e = getUltimoEmprestimo();
		if (e != null) {

			return e.getCliente();
		}

		return null;
	}

	public void setListEmprestimo(List<Emprestimo> listEmprestimo) {
		this.listEmprestimo = listEmprestimo;
	}

}
