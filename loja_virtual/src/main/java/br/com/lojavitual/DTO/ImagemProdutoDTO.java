package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.Objects;

public class ImagemProdutoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String imagemOriginal;
	private String imagemMiniatura;
	private ProdutoDTO produto;
	private PessoaJuridicaDTO empresa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImagemOriginal() {
		return imagemOriginal;
	}
	public void setImagemOriginal(String imagemOriginal) {
		this.imagemOriginal = imagemOriginal;
	}
	public String getImagemMiniatura() {
		return imagemMiniatura;
	}
	public void setImagemMiniatura(String imagemMiniatura) {
		this.imagemMiniatura = imagemMiniatura;
	}
	public ProdutoDTO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}
	public PessoaJuridicaDTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PessoaJuridicaDTO empresa) {
		this.empresa = empresa;
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
		ImagemProdutoDTO other = (ImagemProdutoDTO) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "ImagemProdutoDTO [id=" + id + ", imagemOriginal=" + imagemOriginal + ", imagemMiniatura="
				+ imagemMiniatura + ", produto=" + produto + ", empresa=" + empresa + "]";
	}

	
}
