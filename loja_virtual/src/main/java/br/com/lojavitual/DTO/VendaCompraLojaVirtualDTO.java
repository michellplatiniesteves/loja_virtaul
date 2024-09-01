package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.lojavitual.model.CupDesc;
import br.com.lojavitual.model.Endereco;
import br.com.lojavitual.model.FormaPagamento;
import br.com.lojavitual.model.ItemVendaLoja;
import br.com.lojavitual.model.NotaFiscalVenda;
import br.com.lojavitual.model.Pessoa;
import br.com.lojavitual.model.PessoaFisica;
import br.com.lojavitual.model.PessoaJuridica;
import br.com.lojavitual.model.VendaCompraLojaVirtual;

public class VendaCompraLojaVirtualDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private BigDecimal valorTotal = BigDecimal.ZERO;

	private BigDecimal valorDesc = BigDecimal.ZERO;

	private EnderecoDTO cobranca = new EnderecoDTO();

	private EnderecoDTO entrega = new EnderecoDTO();

	private BigDecimal valorFrete = BigDecimal.ZERO;
	
	private Boolean excluido;

	private List<ItemVendaDTO> itemVendaLoja = new ArrayList<ItemVendaDTO>();

	private PessoaFisica pessoa;

	public PessoaFisica getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

	public void setItemVendaLoja(List<ItemVendaDTO> itemVendaLoja) {
		this.itemVendaLoja = itemVendaLoja;
	}

	public List<ItemVendaDTO> getItemVendaLoja() {
		return itemVendaLoja;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public BigDecimal getValorDesc() {

		if (valorDesc == null) {
			return BigDecimal.ZERO;
		}

		return valorDesc;
	}

	public void setValorDesc(BigDecimal valorDesc) {
		this.valorDesc = valorDesc;
	}

	public EnderecoDTO getCobranca() {
		return cobranca;
	}

	public void setCobranca(EnderecoDTO cobranca) {
		this.cobranca = cobranca;
	}

	public EnderecoDTO getEntrega() {
		return entrega;
	}

	public void setEntrega(EnderecoDTO entrega) {
		this.entrega = entrega;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}
	public Boolean getExcluido() {
		return excluido;
	}
	public VendaCompraLojaVirtualDTO converter(VendaCompraLojaVirtual vendaCompraLojaVirtual) {
		VendaCompraLojaVirtualDTO dto = new VendaCompraLojaVirtualDTO();
		dto.setId(vendaCompraLojaVirtual.getId());
		 dto.setCobranca(cobranca.converter(vendaCompraLojaVirtual.getEnderecoCobranca()));
		 dto.setEntrega(entrega.converter(vendaCompraLojaVirtual.getEnderecoEntrega()));
		dto.setPessoa(vendaCompraLojaVirtual.getPessoa());
		dto.setExcluido(vendaCompraLojaVirtual.getExcluido());
		return dto;
	}

}
