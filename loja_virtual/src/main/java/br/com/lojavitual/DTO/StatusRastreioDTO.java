package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.Objects;

public class StatusRastreioDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String cidade;
	private String estado;
	private String status;
	private String centroDistribuicao;
	private VendaCompraLojaVirtualDTO vendaCompraLojaVirtual;
	private PessoaJuridicaDTO empresa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCentroDistribuicao() {
		return centroDistribuicao;
	}
	public void setCentroDistribuicao(String centroDistribuicao) {
		this.centroDistribuicao = centroDistribuicao;
	}
	public VendaCompraLojaVirtualDTO getVendaCompraLojaVirtual() {
		return vendaCompraLojaVirtual;
	}
	public void setVendaCompraLojaVirtual(VendaCompraLojaVirtualDTO vendaCompraLojaVirtual) {
		this.vendaCompraLojaVirtual = vendaCompraLojaVirtual;
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
		StatusRastreioDTO other = (StatusRastreioDTO) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "StatusRastreioDTO [id=" + id + ", cidade=" + cidade + ", estado=" + estado + ", status=" + status
				+ ", centroDistribuicao=" + centroDistribuicao + ", vendaCompraLojaVirtual=" + vendaCompraLojaVirtual
				+ ", empresa=" + empresa + "]";
	}
	
	

}
