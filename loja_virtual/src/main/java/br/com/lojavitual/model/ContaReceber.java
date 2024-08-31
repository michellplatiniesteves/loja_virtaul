package br.com.lojavitual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.lojavitual.enums.StatusContaReceber;

@Entity
@Table(name = "conta_receber")
@SequenceGenerator(name = "seq_conta_receber", sequenceName = "seq_conta_receber", initialValue = 1, allocationSize = 1)
public class ContaReceber implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_receber")
	private Long id;

	
	@ManyToOne(targetEntity = PessoaFisica.class)
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private PessoaFisica pessoa;
	
	@NotNull(message = "Informe a descrição")
	@NotEmpty(message = "Informe a descrição")
	@Column(nullable = false)
	private String descricao;

	@Enumerated(EnumType.STRING)
	private StatusContaReceber statusContaReceber;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar dtVencimento;

	@Temporal(TemporalType.DATE)
	private Calendar dtPagamento;

	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	private BigDecimal valorDesconto;
	
	
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "empresa_fk"))
	private PessoaJuridica empresa;
	
	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}
	public PessoaJuridica getEmpresa() {
		return empresa;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PessoaFisica getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public StatusContaReceber getStatusContaReceber() {
		return statusContaReceber;
	}
	public void setStatusContaReceber(StatusContaReceber statusContaReceber) {
		this.statusContaReceber = statusContaReceber;
	}
	public Calendar getDtVencimento() {
		return dtVencimento;
	}
	public void setDtVencimento(Calendar dtVencimento) {
		this.dtVencimento = dtVencimento;
	}
	public Calendar getDtPagamento() {
		return dtPagamento;
	}
	public void setDtPagamento(Calendar dtPagamento) {
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
		ContaReceber other = (ContaReceber) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "ContaReceber [id=" + id + ", pessoa=" + pessoa + ", descricao=" + descricao + ", statusContaReceber="
				+ statusContaReceber + ", dtVencimento=" + dtVencimento + ", dtPagamento=" + dtPagamento
				+ ", valorTotal=" + valorTotal + ", valorDesconto=" + valorDesconto + ", empresa=" + empresa + "]";
	}

	
}
