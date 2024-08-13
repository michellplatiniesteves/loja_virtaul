package br.com.lojavitual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vd_cp_loja_virt")
@SequenceGenerator(sequenceName = "seq_vd_cp_loja_virt", name = "seq_vd_cp_loja_virt", allocationSize = 1, initialValue = 1)
public class VendaCompraLojaVirtual implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator ="seq_vd_cp_loja_virt",strategy = GenerationType.SEQUENCE )
	private Long id;
	
	@ManyToOne(targetEntity = PessoaFisica.class)
	@JoinColumn(name = "pessoa_id",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "pessoa_fk"))
	private PessoaFisica pessoa;
	
	@ManyToOne(targetEntity = Endereco.class)
	@JoinColumn(name = "endereco_entrega_id",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "endereco_entrega_fk"))
	private Endereco enderecoEntrega;
	
	@ManyToOne(targetEntity = Endereco.class)
	@JoinColumn(name = "endereco_cobranca_id",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "endereco_cobranca_fk"))
	private Endereco enderecoCobranca;
	
	@Column(nullable = false)
	private BigDecimal valorTotal;
	private BigDecimal valorDesconto;
	
	@ManyToOne(targetEntity = FormaPagamento.class)
	@JoinColumn(name = "forma_pagamento_id",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "forma_pagamento_fk"))
	private FormaPagamento formaPagamento;
	
	@OneToOne
	@JoinColumn(name = "nota_fiscal_venda_id",nullable = true,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "nota_fiscal_venda_fk"))
	private NotaFiscalVenda notaFiscalVenda;
	
	@ManyToOne(targetEntity = CupDesc.class)
	@JoinColumn(name = "cup_desc_id",foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name= "cup_desc_fk"))
	private CupDesc cupDesc;
	
	@Column(nullable = false)
	private Integer diasEntrega;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar dataVenda;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar dataEntrega;
	
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
	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}
	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	public Endereco getEnderecoCobranca() {
		return enderecoCobranca;
	}
	public void setEnderecoCobranca(Endereco enderecoCobranca) {
		this.enderecoCobranca = enderecoCobranca;
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
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public NotaFiscalVenda getNotaFiscalVenda() {
		return notaFiscalVenda;
	}
	public void setNotaFiscalVenda(NotaFiscalVenda notaFiscalVenda) {
		this.notaFiscalVenda = notaFiscalVenda;
	}
	public CupDesc getCupDesc() {
		return cupDesc;
	}
	public void setCupDesc(CupDesc cupDesc) {
		this.cupDesc = cupDesc;
	}
	public Integer getDiasEntrega() {
		return diasEntrega;
	}
	public void setDiasEntrega(Integer diasEntrega) {
		this.diasEntrega = diasEntrega;
	}
	public Calendar getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}
	public Calendar getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Calendar dataEntrega) {
		this.dataEntrega = dataEntrega;
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
		VendaCompraLojaVirtual other = (VendaCompraLojaVirtual) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "VendaCompraLojaVirtual [id=" + id + ", pessoa=" + pessoa + ", enderecoEntrega=" + enderecoEntrega
				+ ", enderecoCobranca=" + enderecoCobranca + ", valorTotal=" + valorTotal + ", valorDesconto="
				+ valorDesconto + ", formaPagamento=" + formaPagamento + ", notaFiscalVenda=" + notaFiscalVenda
				+ ", cupDesc=" + cupDesc + ", diasEntrega=" + diasEntrega + ", dataVenda=" + dataVenda
				+ ", dataEntrega=" + dataEntrega + ", empresa=" + empresa + "]";
	}
	
	
}
