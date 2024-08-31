package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.Objects;

import br.com.lojavitual.model.FormaPagamento;

public class FormaPagamentoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;
	private PessoaJuridicaDTO empresa;
	
	public FormaPagamentoDTO converter (FormaPagamento formaPagamento) {
		FormaPagamentoDTO formaPagamentoDTO = new FormaPagamentoDTO();
		PessoaJuridicaDTO pessoaJuridicaDTO = new PessoaJuridicaDTO();
		formaPagamentoDTO.setId(formaPagamento.getId());
		formaPagamentoDTO.setDescricao(formaPagamento.getDescricao());
		formaPagamentoDTO.setEmpresa(pessoaJuridicaDTO.coverter(formaPagamento.getEmpresa()));
		return formaPagamentoDTO;
	} 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public PessoaJuridicaDTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PessoaJuridicaDTO empresa) {
		this.empresa = empresa;
	}
	@Override
	public String toString() {
		return "FormaPagamentoDTO [id=" + id + ", descricao=" + descricao + ", empresa=" + empresa + "]";
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
		FormaPagamentoDTO other = (FormaPagamentoDTO) obj;
		return Objects.equals(id, other.id);
	}
	

}
