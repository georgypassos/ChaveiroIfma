package entidade;

import java.io.Serializable;
import java.lang.Integer;
import java.util.List;

import javax.persistence.*;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idcliente;
	
	@Column(length=50)
	private String nome;
	
	@Column(length=11)
	private String cpf;
	
	private String senha; 
	
	@Column(length=50)
	private String email;
	
	@Column(length=10)
	private String telefone;
	
	@Column(length=1)
	private Integer perfil;

    @OneToMany(mappedBy = "cliente")
    private List<Emprestimo> listEmprestimo;
	
	//não sei quais os perfis ao certo, alterem aqui...
	private static final int PERFIL_USUARIO = 0;
	private static final int PERFIL_ADMINISTRADOR = 1;
	private static final int PERFIL_CLIENTE = 2;
	//...

	public Cliente() {
		super();
	}
	
	public Integer getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getPerfil() {
		return perfil;
	}

	//Retorna a String com o tipo do perfil de usuário
	public String getPerfilStr(){
		
		if(perfil != null){
			
			if(perfil == PERFIL_USUARIO)
				return "Usuário";
			if(perfil == PERFIL_ADMINISTRADOR)
				return "Administrador";
			if(perfil == PERFIL_CLIENTE)
				return "Cliente";
			
		}
		
		return "";
	}
	
	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public List<Emprestimo> getListEmprestimo() {
		return listEmprestimo;
	}

	public void setListEmprestimo(List<Emprestimo> listEmprestimo) {
		this.listEmprestimo = listEmprestimo;
	}
    
	
	
}
