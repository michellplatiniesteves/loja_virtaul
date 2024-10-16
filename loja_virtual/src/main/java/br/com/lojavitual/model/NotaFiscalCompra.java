package br.com.lojavitual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "nota_fiscal_compra")
@SequenceGenerator(sequenceName = "seq_nota_fiscal_compra", name = "seq_nota_fiscal_compra", allocationSize = 1, initialValue = 1)
public class NotaFiscalCompra  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator ="seq_nota_fiscal_compra",strategy = GenerationType.SEQUENCE )
	private Long id;
	@Column(nullable = false)
	private String numeroNota;
	@Column(nullable = false)
	private String serieNota;
	
	private String descricaoObs;
	@Column(nullable = false)
	private BigDecimal valorTotal;

	private BigDecimal valorDesconto;
	@Column(nullable = false)
	private BigDecimal valorIcms;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar dataCompra;
	
	
	@ManyToOne(targetEntity = PessoaFisica.class)
	@JoinColumn(name = "pessoa_id",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "pessoa_fk"))
	private PessoaFisica pessoa;
	
	@ManyToOne
	@JoinColumn(name = "conta_pagar_id",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "conta_pagar_fk"))
	private ContaPagar contaPagar;
	
	
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
	public String getNumeroNota() {
		return numeroNota;
	}
	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}
	public String getSerieNota() {
		return serieNota;
	}
	public void setSerieNota(String serieNota) {
		this.serieNota = serieNota;
	}
	public String getDescricaoObs() {
		return descricaoObs;
	}
	public void setDescricaoObs(String descricaoObs) {
		this.descricaoObs = descricaoObs;
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
	public BigDecimal getValorIcms() {
		return valorIcms;
	}
	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms;
	}
	public Calendar getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Calendar dataCompra) {
		this.dataCompra = dataCompra;
	}
	public PessoaFisica getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}
	public ContaPagar getContaPagar() {
		return contaPagar;
	}
	public void setContaPagar(ContaPagar contaPagar) {
		this.contaPagar = contaPagar;
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
		NotaFiscalCompra other = (NotaFiscalCompra) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "NotaFiscalCompra [id=" + id + ", numeroNota=" + numeroNota + ", serieNota=" + serieNota
				+ ", descricaoObs=" + descricaoObs + ", valorTotal=" + valorTotal + ", valorDesconto=" + valorDesconto
				+ ", valorIcms=" + valorIcms + ", dataCompra=" + dataCompra + ", pessoa=" + pessoa + ", contaPagar="
				+ contaPagar + ", empresa=" + empresa + "]";
	}

	
}
