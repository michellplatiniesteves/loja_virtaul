package br.com.lojavitual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.lojavitual.enums.StatusContaPagar;

@Entity
@Table(name = "conta_pagar")
@SequenceGenerator(name = "seq_conta_pagar", sequenceName = "seq_conta_pagar", initialValue = 1, allocationSize = 1)
public class ContaPagar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_pagar")
	private Long id;

	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "pessoa_forn_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_forn_fk"))
	private Pessoa pessoaFornecedor;

	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private Pessoa pessoa;
	
	@NotNull(message = "Informe a descrição")
	@NotEmpty(message = "Informe a descrição")
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusContaPagar statusContPagar;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtVencimento;

	@Temporal(TemporalType.DATE)
	private Date dtPagamento;

	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	private BigDecimal valorDesconto;
	
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "empresa_id",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "empresa_fk"))
	private Pessoa empresa;
	
	public void setEmpresa(Pessoa empresa) {
		this.empresa = empresa;
	}
	
	public Pessoa getEmpresa() {
		return empresa;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pessoa getPessoaFornecedor() {
		return pessoaFornecedor;
	}
	public void setPessoaFornecedor(Pessoa pessoaFornecedor) {
		this.pessoaFornecedor = pessoaFornecedor;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public StatusContaPagar getStatusContPagar() {
		return statusContPagar;
	}
	public void setStatusContPagar(StatusContaPagar statusContPagar) {
		this.statusContPagar = statusContPagar;
	}
	public Date getDtVencimento() {
		return dtVencimento;
	}
	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}
	public Date getDtPagamento() {
		return dtPagamento;
	}
	public void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaPagar other = (ContaPagar) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "ContaPagar [id=" + id + ", pessoaFornecedor=" + pessoaFornecedor + ", pessoa=" + pessoa + ", descricao="
				+ descricao + ", statusContPagar=" + statusContPagar + ", dtVencimento=" + dtVencimento
				+ ", dtPagamento=" + dtPagamento + ", valorTotal=" + valorTotal + ", valorDesconto=" + valorDesconto
				+ ", empresa=" + empresa + "]";
	}

	

	
}
