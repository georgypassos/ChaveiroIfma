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
	
	private Boolean status;

    @OneToMany(mappedBy = "sala")
    private List<Emprestimo> listEmprestimo;

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Emprestimo> getListEmprestimo() {
		return listEmprestimo;
	}
	
	public Emprestimo getUltimoEmprestimo(){
		
		if(listEmprestimo != null && listEmprestimo.size()>0){
			return listEmprestimo.get(listEmprestimo.size()-1);
		}
		
		return null;
	}
	
	public Cliente getUltimoCliente(){
		
		Emprestimo e = getUltimoEmprestimo();
		if(e != null){
			
			return e.getCliente();
		}
		
		return null;
	}

	public void setListEmprestimo(List<Emprestimo> listEmprestimo) {
		this.listEmprestimo = listEmprestimo;
	}
	
}
