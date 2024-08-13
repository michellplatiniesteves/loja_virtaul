package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.Objects;

public class AvaliacaoProdutoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer nota;
	private Long pessoa;
	private Long produto;
	private Long empresa;
	private String descricao;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public Long getPessoa() {
		return pessoa;
	}
	public void setPessoa(Long pessoa) {
		this.pessoa = pessoa;
	}
	public Long getProduto() {
		return produto;
	}
	public void setProduto(Long produto) {
		this.produto = produto;
	}
	public Long getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		AvaliacaoProdutoDTO other = (AvaliacaoProdutoDTO) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "AvaliacaoProdutoDTO [id=" + id + ", nota=" + nota + ", pessoa=" + pessoa + ", produto=" + produto
				+ ", empresa=" + empresa + ", descricao=" + descricao + "]";
	}
	
	
}
