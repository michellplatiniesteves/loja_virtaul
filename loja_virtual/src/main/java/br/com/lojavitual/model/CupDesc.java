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

@Entity
@Table(name = "cup_desc")
@SequenceGenerator(allocationSize = 1,initialValue = 1,name = "seq_cup_desc",sequenceName = "seq_cup_desc")
public class CupDesc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator ="seq_cup_desc",strategy = GenerationType.SEQUENCE )
	private Long id;
	
	@Column(nullable = false)
	private String codDesc;
	private BigDecimal valorRealDesc;
	private BigDecimal percPercentDesco;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar dataValidadeCupom;
	
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
		CupDesc other = (CupDesc) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "CupDesc [id=" + id + ", codDesc=" + codDesc + ", valorRealDesc=" + valorRealDesc + ", percPercentDesco="
				+ percPercentDesco + ", dataValidadeCupom=" + dataValidadeCupom + ", empresa=" + empresa + "]";
	}
	
	

}
