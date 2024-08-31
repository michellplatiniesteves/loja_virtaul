package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MarcaProdutoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nomeDesc;
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
		MarcaProdutoDTO other = (MarcaProdutoDTO) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "MarcaProdutoDTO [id=" + id + ", nomeDesc=" + nomeDesc + "]";
	}
	
	

}
