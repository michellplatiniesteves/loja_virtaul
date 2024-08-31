package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.lojavitual.enums.TipoEndereco;
import br.com.lojavitual.model.Endereco;
import br.com.lojavitual.model.PessoaFisica;
import br.com.lojavitual.model.PessoaJuridica;

public class EnderecoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private Long id;
	private String ruaLogra; 
	private String cep; 
	private String numero; 
	private String bairro; 
	private String uf; 
	private String cidade;
	private String complemento; 
	private TipoEndereco tipoEndereco;
	private PessoaFisicaDTO pessoa = new PessoaFisicaDTO();
	private PessoaJuridicaDTO empresa = new PessoaJuridicaDTO();
	
	public EnderecoDTO converter(Endereco endereco) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		enderecoDTO.setId(endereco.getId());
		enderecoDTO.setRuaLogra(endereco.getRuaLogra());
		enderecoDTO.setCep(endereco.getCep());
		enderecoDTO.setNumero(endereco.getNumero());
		enderecoDTO.setBairro(endereco.getBairro());
		enderecoDTO.setUf(endereco.getUf());
		enderecoDTO.setCidade(endereco.getCidade());
		enderecoDTO.setComplemento(endereco.getComplemento());
		enderecoDTO.setPessoa(pessoa.converter(endereco.getPessoa()));
		enderecoDTO.setEmpresa(empresa.coverter(endereco.getEmpresa()));
		return enderecoDTO;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRuaLogra() {
		return ruaLogra;
	}
	public void setRuaLogra(String ruaLogra) {
		this.ruaLogra = ruaLogra;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}
	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
	public PessoaFisicaDTO getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaFisicaDTO pessoa) {
		this.pessoa = pessoa;
	}
	public PessoaJuridicaDTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PessoaJuridicaDTO empresa) {
		this.empresa = empresa;
	}
	@Override
	public String toString() {
		return "EnderecoDTO [id=" + id + ", ruaLogra=" + ruaLogra + ", cep=" + cep + ", numero=" + numero + ", bairro="
				+ bairro + ", uf=" + uf + ", cidade=" + cidade + ", complemento=" + complemento + ", tipoEndereco="
				+ tipoEndereco + ", pessoa=" + pessoa + ", empresa=" + empresa + "]";
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
		EnderecoDTO other = (EnderecoDTO) obj;
		return Objects.equals(id, other.id);
	}

	
}
