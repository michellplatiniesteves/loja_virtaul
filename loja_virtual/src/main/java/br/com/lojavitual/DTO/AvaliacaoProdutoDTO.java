package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.Objects;

import br.com.lojavitual.model.AvaliacaoProduto;
import br.com.lojavitual.model.PessoaFisica;
import br.com.lojavitual.model.PessoaJuridica;

public class AvaliacaoProdutoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer nota;
	private Long idpessoaFisica;
	private Long idproduto;
	private Long idempresa;
	private String descricao;
	
	
	public AvaliacaoProdutoDTO coverterDto(AvaliacaoProduto avaliacaoProduto) {
		AvaliacaoProdutoDTO dto = new AvaliacaoProdutoDTO();
		dto.setId(avaliacaoProduto.getId());
		dto.setNota(avaliacaoProduto.getNota());
		dto.setDescricao(avaliacaoProduto.getDescricao());
		dto.setidempresa(avaliacaoProduto.getEmpresa().getId());
		dto.setidproduto(avaliacaoProduto.getProduto().getId());
		dto.setidpessoaFisica(avaliacaoProduto.getPessoa().getId());
		
		return dto;
	} 
		
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
	public Long getidpessoaFisica() {
		return idpessoaFisica;
	}
	public void setidpessoaFisica(Long idpessoaFisica) {
		this.idpessoaFisica = idpessoaFisica;
	}
	public Long getidproduto() {
		return idproduto;
	}
	public void setidproduto(Long idproduto) {
		this.idproduto = idproduto;
	}
	public Long getidempresa() {
		return idempresa;
	}
	public void setidempresa(Long idempresa) {
		this.idempresa = idempresa;
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
		return "AvaliacaoProdutoDTO [id=" + id + ", nota=" + nota + ", idpessoaFisica=" + idpessoaFisica
				+ ", idproduto=" + idproduto + ", idempresa=" + idempresa + ", descricao=" + descricao + "]";
	}

	
	
}
