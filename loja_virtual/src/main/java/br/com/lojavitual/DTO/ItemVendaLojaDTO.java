package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.Objects;

import br.com.lojavitual.model.ItemVendaLoja;

public class ItemVendaLojaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Double quantidade;
	private VendaCompraLojaVirtualDTO vendaCompraLojaVirtual;
	private ProdutoDTO produto;
	private PessoaJuridicaDTO empresa;
	
	
	public ItemVendaLojaDTO converter(ItemVendaLoja itemVendaLoja ) {
		ItemVendaLojaDTO dto = new ItemVendaLojaDTO();
		PessoaJuridicaDTO empresa = new PessoaJuridicaDTO();

		dto.setId(itemVendaLoja.getId());
		dto.setEmpresa(empresa.coverter(itemVendaLoja.getEmpresa()));
		dto.setQuantidade(itemVendaLoja.getQuantidade());
		return dto;
	}
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
	public VendaCompraLojaVirtualDTO getVendaCompraLojaVirtual() {
		return vendaCompraLojaVirtual;
	}
	public void setVendaCompraLojaVirtual(VendaCompraLojaVirtualDTO vendaCompraLojaVirtual) {
		this.vendaCompraLojaVirtual = vendaCompraLojaVirtual;
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
		ItemVendaLojaDTO other = (ItemVendaLojaDTO) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "ItemVendaLojaDTO [id=" + id + ", quantidade=" + quantidade + ", vendaCompraLojaVirtual="
				+ vendaCompraLojaVirtual + ", produto=" + produto + ", empresa=" + empresa + "]";
	}
	
	

}
