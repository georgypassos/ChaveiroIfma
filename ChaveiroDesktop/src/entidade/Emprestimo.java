package entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Emprestimo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idemprestimo;
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataRetirada;
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataEntrega;

	@ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "clienteRetirada")
    private Cliente clienteRetirada;

	@ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "clienteEntrega")
    private Cliente clienteEntrega;

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn(name = "sala")
    private Sala sala;

	public Integer getIdemprestimo() {
		return idemprestimo;
	}

	public void setIdemprestimo(Integer idemprestimo) {
		this.idemprestimo = idemprestimo;
	}

	public Date getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Cliente getClienteRetirada() {
		return clienteRetirada;
	}

	public void setClienteRetirada(Cliente clienteRetirada) {
		this.clienteRetirada = clienteRetirada;
	}

	public Cliente getClienteEntrega() {
		return clienteEntrega;
	}

	public void setClienteEntrega(Cliente clienteEntrega) {
		this.clienteEntrega = clienteEntrega;
	}
	
}
