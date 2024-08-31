package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

import br.com.lojavitual.model.ContaPagar;
import br.com.lojavitual.model.PessoaJuridica;

public class NotaFiscalCompraDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numeroNota;
	private String serieNota;
	private String descricaoObs;
	private BigDecimal valorTotal;
	private BigDecimal valorIcms;
	private Calendar dataCompra;
	private PessoaFisicaDTO pessoa;
	private ContaPagarDTO contaPagar;
	private PessoaJuridicaDTO empresa;
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
	public PessoaFisicaDTO getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaFisicaDTO pessoa) {
		this.pessoa = pessoa;
	}
	public ContaPagarDTO getContaPagar() {
		return contaPagar;
	}
	public void setContaPagar(ContaPagarDTO contaPagar) {
		this.contaPagar = contaPagar;
	}
	public PessoaJuridicaDTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PessoaJuridicaDTO empresa) {
		this.empresa = empresa;
	}
	@Override
	public String toString() {
		return "NotaFiscalCompraDTO [id=" + id + ", numeroNota=" + numeroNota + ", serieNota=" + serieNota
				+ ", descricaoObs=" + descricaoObs + ", valorTotal=" + valorTotal + ", valorIcms=" + valorIcms
				+ ", dataCompra=" + dataCompra + ", pessoa=" + pessoa + ", contaPagar=" + contaPagar + ", empresa="
				+ empresa + "]";
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
		NotaFiscalCompraDTO other = (NotaFiscalCompraDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
