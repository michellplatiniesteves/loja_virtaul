package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.lojavitual.model.NotaFiscalVenda;
import br.com.lojavitual.model.PessoaJuridica;
import br.com.lojavitual.model.VendaCompraLojaVirtual;

public class NotaFiscalVendaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String numero;
	private String serie;
	private String tipo;
	private VendaCompraLojaVirtualDTO vendaCompraLojaVirtual;
	private String xml;
	private String pdf;
	private PessoaJuridicaDTO empresa;
	
	public NotaFiscalVendaDTO converter(NotaFiscalVenda notaFiscalVenda ) {
		NotaFiscalVendaDTO dto = new NotaFiscalVendaDTO();
		PessoaJuridicaDTO empresa = new PessoaJuridicaDTO();

		dto.setId(notaFiscalVenda.getId());
		dto.setEmpresa(empresa.coverter(notaFiscalVenda.getEmpresa()));
		dto.setNumero(notaFiscalVenda.getNumero());
		return dto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public VendaCompraLojaVirtualDTO getVendaCompraLojaVirtual() {
		return vendaCompraLojaVirtual;
	}
	public void setVendaCompraLojaVirtual(VendaCompraLojaVirtualDTO vendaCompraLojaVirtual) {
		this.vendaCompraLojaVirtual = vendaCompraLojaVirtual;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
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
		NotaFiscalVendaDTO other = (NotaFiscalVendaDTO) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "NotaFiscalVendaDTO [id=" + id + ", numero=" + numero + ", serie=" + serie + ", tipo=" + tipo
				+ ", vendaCompraLojaVirtual=" + vendaCompraLojaVirtual + ", xml=" + xml + ", pdf=" + pdf + ", empresa="
				+ empresa + "]";
	}
	
	
	
	

}
