package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.Objects;

import br.com.lojavitual.model.PessoaJuridica;

public class CategoriaProdutoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nomeDesc;
	private PessoaJuridicaDTO empresa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeDesc() {
		return nomeDesc;
	}
	public void setNomeDesc(String nomeDesc) {
		this.nomeDesc = nomeDesc;
	}
	public PessoaJuridicaDTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PessoaJuridicaDTO empresa) {
		this.empresa = empresa;
	}
	@Override
	public String toString() {
		return "CategoriaProdutoDTO [id=" + id + ", nomeDesc=" + nomeDesc + ", empresa=" + empresa + "]";
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
		CategoriaProdutoDTO other = (CategoriaProdutoDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
