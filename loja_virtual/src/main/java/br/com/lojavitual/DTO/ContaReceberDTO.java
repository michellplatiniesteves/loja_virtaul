package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

import br.com.lojavitual.enums.StatusContaReceber;

public class ContaReceberDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;
	private PessoaFisicaDTO pessoa;
	private String descricao;
	private StatusContaReceber statusContaReceber;
	private Calendar dtVencimento;
	private Calendar dtPagamento;
	private BigDecimal valorTotal;
	private BigDecimal valorDesconto;
	private PessoaJuridicaDTO empresa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PessoaFisicaDTO getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaFisicaDTO pessoa) {
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
	public PessoaJuridicaDTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PessoaJuridicaDTO empresa) {
		this.empresa = empresa;
	}
	@Override
	public String toString() {
		return "ContaReceberDTO [id=" + id + ", pessoa=" + pessoa + ", descricao=" + descricao + ", statusContaReceber="
				+ statusContaReceber + ", dtVencimento=" + dtVencimento + ", dtPagamento=" + dtPagamento
				+ ", valorTotal=" + valorTotal + ", valorDesconto=" + valorDesconto + ", empresa=" + empresa + "]";
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
		ContaReceberDTO other = (ContaReceberDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
