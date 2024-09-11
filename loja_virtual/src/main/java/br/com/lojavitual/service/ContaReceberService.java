package br.com.lojavitual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.ContaPagar;
import br.com.lojavitual.model.ContaReceber;
import br.com.lojavitual.repository.ContaReceberRepository;

@Service
public class ContaReceberService {
	
	@Autowired
	private ContaReceberRepository contaReceberRepository;

	public List<ContaReceber> buscarPorDesc(String desc) {
		List<ContaReceber> listabuscarPorDesc =  contaReceberRepository.buscarPorDesc(desc);
		return listabuscarPorDesc;
	}

	public ContaReceber buscarPorPessoa(Long id) {
		ContaReceber pessoabuscarPorPessoa= contaReceberRepository.buscarPorPessoa(id);
		return pessoabuscarPorPessoa;
	}


	public ContaReceber buscarPorEmpresa(Long id) {
		ContaReceber pessoabuscarPorEmpresa= contaReceberRepository.buscarPorEmpresa(id);
		return pessoabuscarPorEmpresa;
	}

	public ContaReceber buscarContaReceber(Long id) {
		ContaReceber pessoabuscarContaReceber= contaReceberRepository.buscarContaReceber(id);
		return pessoabuscarContaReceber;
	}

	public void deletarContaPagar(Long id) {
		contaReceberRepository.deleteById(id);
		
	}

	public ContaReceber salvarContaPagar( ContaReceber contaReceber) throws ExceptionMentoriaJava {
		if(contaReceber == null) {
			throw new ExceptionMentoriaJava("Informe uma conta a pagar");
		}
		
		if(contaReceber.getId() == null && contaReceberRepository.existeContaPagar(contaReceber.getDescricao())) {
			throw new ExceptionMentoriaJava("Já existe uma conta a pagar com essa descrição :" + contaReceber.getDescricao());
		}
		if(contaReceber.getEmpresa().getId() == null || contaReceber.getEmpresa().getId()<0) {
			throw new ExceptionMentoriaJava("Informe a Empresa para a conta a pagar");
		}
		if(contaReceber.getPessoa().getId() == null || contaReceber.getPessoa().getId()<0) {
			throw new ExceptionMentoriaJava("Informe uma Pessoa para a conta a pagar");
		}
		contaReceber = contaReceberRepository.saveAndFlush(contaReceber);
		return contaReceber;
	}

}
