package br.com.lojavitual.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaJuridica extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	@CNPJ( message = "CNPJ invalido")
	private String cnpj;
	
	@NotNull(message = "Informe a inscEstadual")
	@NotEmpty(message = "Informe a inscEstadual")
	@Column(nullable = false)
	private String inscEstadual;
	
	@NotNull(message = "Informe a inscMunicipal")
	@NotEmpty(message = "Informe a inscMunicipal")
	private String inscMunicipal;
	
	@NotNull(message = "Informe o nomeFastasia")
	@NotEmpty(message = "Informe o nomeFastasia")
	@Column(nullable = false)
	private String nomeFastasia;
	
	@NotNull(message = "Informe a razaoSocial")
	@NotEmpty(message = "Informe a razaoSocial")
    @Column(nullable = false)
	private String razaoSocial;
	
	@Column(nullable = false)
	private String categoria;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscEstadual() {
		return inscEstadual;
	}
	public void setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
	}
	public String getInscMunicipal() {
		return inscMunicipal;
	}
	public void setInscMunicipal(String inscMunicipal) {
		this.inscMunicipal = inscMunicipal;
	}
	public String getNomeFastasia() {
		return nomeFastasia;
	}
	public void setNomeFastasia(String nomeFastasia) {
		this.nomeFastasia = nomeFastasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cnpj);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaJuridica other = (PessoaJuridica) obj;
		return Objects.equals(cnpj, other.cnpj);
	}
	@Override
	public String toString() {
		return "PessoaJuridica [cnpj=" + cnpj + ", inscEstadual=" + inscEstadual + ", inscMunicipal=" + inscMunicipal
				+ ", nomeFastasia=" + nomeFastasia + ", razaoSocial=" + razaoSocial + ", categoria=" + categoria + "]";
	}
	
	
}
