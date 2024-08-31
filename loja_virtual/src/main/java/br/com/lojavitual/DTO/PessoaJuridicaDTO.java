package br.com.lojavitual.DTO;

import java.io.Serializable;

import br.com.lojavitual.model.PessoaJuridica;

public class PessoaJuridicaDTO extends PessoaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cnpj;
	private String inscEstadual;
	private String inscMunicipal;
	private String nomeFastasia;
	private String razaoSocial;
	private String categoria;
	
	public PessoaJuridicaDTO coverter(PessoaJuridica pessoaJuridica) {
		PessoaJuridicaDTO pessoaJuridicaDTO = new PessoaJuridicaDTO();
		pessoaJuridicaDTO.setId(pessoaJuridica.getId());
		pessoaJuridicaDTO.setNome(pessoaJuridica.getNome());
		pessoaJuridicaDTO.setCnpj(pessoaJuridica.getCnpj());
		pessoaJuridicaDTO.setInscEstadual(pessoaJuridica.getInscEstadual());
		pessoaJuridicaDTO.setInscMunicipal(pessoaJuridica.getInscMunicipal());
		pessoaJuridicaDTO.setNomeFastasia(pessoaJuridica.getNomeFastasia());
		pessoaJuridicaDTO.setRazaoSocial(pessoaJuridica.getRazaoSocial());
		pessoaJuridicaDTO.setCategoria(pessoaJuridica.getCategoria());
		
		return pessoaJuridicaDTO;
		
	} 
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
	public String toString() {
		return "PessoaJuridicaDTO [cnpj=" + cnpj + ", inscEstadual=" + inscEstadual + ", inscMunicipal=" + inscMunicipal
				+ ", nomeFastasia=" + nomeFastasia + ", razaoSocial=" + razaoSocial + ", categoria=" + categoria + "]";
	}
	
	
}
