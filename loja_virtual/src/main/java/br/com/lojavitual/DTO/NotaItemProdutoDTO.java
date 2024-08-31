package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.Objects;

public class NotaItemProdutoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;

	private Double quantidade;
	private ProdutoDTO produto;
	private NotaFiscalCompraDTO notaFiscalCompra;
	private PessoaJuridicaDTO empresa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public ProdutoDTO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}
	public NotaFiscalCompraDTO getNotaFiscalCompra() {
		return notaFiscalCompra;
	}
	public void setNotaFiscalCompra(NotaFiscalCompraDTO notaFiscalCompra) {
		this.notaFiscalCompra = notaFiscalCompra;
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
		NotaItemProdutoDTO other = (NotaItemProdutoDTO) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "NotaItemProdutoDTO [id=" + id + ", quantidade=" + quantidade + ", produto=" + produto
				+ ", notaFiscalCompra=" + notaFiscalCompra + ", empresa=" + empresa + "]";
	}
	
	

}
