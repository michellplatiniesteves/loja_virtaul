package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.lojavitual.model.PessoaJuridica;

public class CupDescDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String codDesc;
	private BigDecimal valorRealDesc;
	private BigDecimal percPercentDesco;
	private Calendar dataValidadeCupom;
	private PessoaJuridicaDTO empresa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodDesc() {
		return codDesc;
	}
	public void setCodDesc(String codDesc) {
		this.codDesc = codDesc;
	}
	public BigDecimal getValorRealDesc() {
		return valorRealDesc;
	}
	public void setValorRealDesc(BigDecimal valorRealDesc) {
		this.valorRealDesc = valorRealDesc;
	}
	public BigDecimal getPercPercentDesco() {
		return percPercentDesco;
	}
	public void setPercPercentDesco(BigDecimal percPercentDesco) {
		this.percPercentDesco = percPercentDesco;
	}
	public Calendar getDataValidadeCupom() {
		return dataValidadeCupom;
	}
	public void setDataValidadeCupom(Calendar dataValidadeCupom) {
		this.dataValidadeCupom = dataValidadeCupom;
	}
	public PessoaJuridicaDTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PessoaJuridicaDTO empresa) {
		this.empresa = empresa;
	}
	@Override
	public String toString() {
		return "CupDescDTO [id=" + id + ", codDesc=" + codDesc + ", valorRealDesc=" + valorRealDesc
				+ ", percPercentDesco=" + percPercentDesco + ", dataValidadeCupom=" + dataValidadeCupom + ", empresa="
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
		CupDescDTO other = (CupDescDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
