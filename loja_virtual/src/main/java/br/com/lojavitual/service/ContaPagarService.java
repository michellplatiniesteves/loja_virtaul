package br.com.lojavitual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.ContaPagar;
import br.com.lojavitual.repository.ContaPagarRepository;

@Service
public class ContaPagarService {
	
	@Autowired
	private ContaPagarRepository contaPagarRepository;

	public List<ContaPagar> buscarPorDesc(String desc) {
		List<ContaPagar> listabuscarPorDesc =  contaPagarRepository.buscarPorDesc(desc);
		return listabuscarPorDesc;
	}

	public ContaPagar buscarPorPessoa(Long id) {
		ContaPagar pessoabuscarPorPessoa= contaPagarRepository.buscarPorPessoa(id);
		return pessoabuscarPorPessoa;
	}

	public ContaPagar buscarPorFornecedor(Long id) {
		ContaPagar pessoabuscarPorFornecedor= contaPagarRepository.buscarPorFornecedor(id);
		return pessoabuscarPorFornecedor;
	}

	public ContaPagar buscarPorEmpresa(Long id) {
		ContaPagar pessoabuscarPorEmpresa= contaPagarRepository.buscarPorEmpresa(id);
		return pessoabuscarPorEmpresa;
	}

	public ContaPagar buscarContaPagar(Long id) {
		ContaPagar pessoabuscarContaPagar= contaPagarRepository.buscarContaPagar(id);
		return pessoabuscarContaPagar;
	}

	public void deletarContaPagar(Long id) {
		contaPagarRepository.deleteById(id);
		
	}

	public ContaPagar salvarContaPagar( ContaPagar contaPagar) throws ExceptionMentoriaJava {
		if(contaPagar == null) {
			throw new ExceptionMentoriaJava("Informe uma conta a pagar");
		}
		
		if(contaPagar.getId() == null && contaPagarRepository.existeContaPagar(contaPagar.getDescricao())) {
			throw new ExceptionMentoriaJava("Já existe uma conta a pagar com essa descrição :" + contaPagar.getDescricao());
		}
		if(contaPagar.getEmpresa().getId() == null || contaPagar.getEmpresa().getId()<0) {
			throw new ExceptionMentoriaJava("Informe a Empresa para a conta a pagar");
		}
		if(contaPagar.getPessoa().getId() == null || contaPagar.getPessoa().getId()<0) {
			throw new ExceptionMentoriaJava("Informe uma Pessoa para a conta a pagar");
		}
		if(contaPagar.getPessoaFornecedor().getId() == null || contaPagar.getPessoaFornecedor().getId()<0) {
			throw new ExceptionMentoriaJava("Informe um Fornecedor para a conta a pagar");
		}
		contaPagar = contaPagarRepository.save(contaPagar);
		return contaPagar;
	}

}
