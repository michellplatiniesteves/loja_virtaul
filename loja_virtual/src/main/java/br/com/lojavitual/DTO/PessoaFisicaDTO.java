package br.com.lojavitual.DTO;

import java.io.Serializable;
import java.util.Calendar;

import br.com.lojavitual.model.PessoaFisica;

public class PessoaFisicaDTO extends PessoaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private Calendar dataNascimento;
	
	public PessoaFisicaDTO converter(PessoaFisica pessoaFisica) {
		PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
		pessoaFisicaDTO.setCpf(pessoaFisica.getCpf());
		pessoaFisicaDTO.setDataNascimento(pessoaFisica.getDataNascimento());
		pessoaFisicaDTO.setEmail(pessoaFisica.getEmail());
		pessoaFisicaDTO.setId(pessoaFisica.getId());
		pessoaFisicaDTO.setNome(pessoaFisica.getNome());
		pessoaFisicaDTO.setTelefone(pessoaFisica.getTelefone());
		
		return pessoaFisicaDTO;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	@Override
	public String toString() {
		return "PessoaFisicaDTO [cpf=" + cpf + ", dataNascimento=" + dataNascimento + "]";
	}

}
