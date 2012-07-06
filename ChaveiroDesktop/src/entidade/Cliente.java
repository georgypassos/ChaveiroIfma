package entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idcliente;
	
	@Column(length=50)
	private String nome;
	
	@Column(length=14)
	private String cpf;
	
	private String senha; 
	
	@Column(length=50)
	private String email;
	
	@Column(length=13)
	private String telefone;
	
	@Column(length=1)
	private Integer perfil;

    @OneToMany(mappedBy = "clienteRetirada")
    private List<Emprestimo> listEmprestimoRetirada;

    @OneToMany(mappedBy = "clienteEntrega")
    private List<Emprestimo> listEmprestimoEntrega;
	
	public static final int PERFIL_USUARIO = 0;
	public static final int PERFIL_ADMINISTRADOR = 1;
	public static final int PERFIL_CLIENTE = 2;
	
	public static final int TAMANHO_MIN_SENHA = 4;
	public static final int TAMANHO_MAX_SENHA = 6;
	public static final int TAMANHO_MAX_NOME = 15;

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
		return nome.toUpperCase();
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

	public List<Emprestimo> getListEmprestimoRetirada() {
		return listEmprestimoRetirada;
	}

	public void setListEmprestimoRetirada(List<Emprestimo> listEmprestimoRetirada) {
		this.listEmprestimoRetirada = listEmprestimoRetirada;
	}

	public List<Emprestimo> getListEmprestimoEntrega() {
		return listEmprestimoEntrega;
	}

	public void setListEmprestimoEntrega(List<Emprestimo> listEmprestimoEntrega) {
		this.listEmprestimoEntrega = listEmprestimoEntrega;
	}
	
}
