package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.lojavitual.model.PessoaJuridica;

public class PessoaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return "PessoaDTO [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone
				+ ", tipoPessoa=" + tipoPessoa + ", enderecos=" + enderecos + ", empresa=" + empresa + "]";
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
		PessoaDTO other = (PessoaDTO) obj;
		return Objects.equals(id, other.id);
	}
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String tipoPessoa;
	private List<EnderecoDTO> enderecos = new ArrayList<EnderecoDTO>();
	private PessoaJuridica empresa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}
	public PessoaJuridica getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}
	
	
}
